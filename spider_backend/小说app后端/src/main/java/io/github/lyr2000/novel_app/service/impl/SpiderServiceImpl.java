package io.github.lyr2000.novel_app.service.impl;

import io.github.lyr2000.novel_app.mapper.BookInfoMapper;
import io.github.lyr2000.novel_app.mapper.IndexPageMapper;
import io.github.lyr2000.novel_app.mapper.NovelDetailPageMapper;
import io.github.lyr2000.novel_app.mapper.custom.BookInfoMapperCustom;
import io.github.lyr2000.novel_app.mapper.custom.IndexPageMapperCustom;
import io.github.lyr2000.novel_app.mapper.custom.NovelDetailPageMapperCustom;
import io.github.lyr2000.novel_app.pojo.dto.*;
import io.github.lyr2000.novel_app.pojo.po.BookInfo;
import io.github.lyr2000.novel_app.pojo.po.IndexPage;
import io.github.lyr2000.novel_app.pojo.po.NovelDetailPage;
import io.github.lyr2000.novel_app.service.SpiderService;
import io.github.lyr2000.novel_app.util.OkHttpClientUtil;
import io.github.lyr2000.novel_app.util.ParseNovelUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author lyr
 * @create 2021/4/13 20:40
 */
@Service
@RequiredArgsConstructor
public class SpiderServiceImpl implements SpiderService {

    private final OkHttpClientUtil okHttpClientUtil;
    private final ParseNovelUtil parseNovelUtil;
    public static final String searchUrl = "https://www.biqugeu.net/searchbook.php?keyword=";

    private final BookInfoMapper bookInfoMapper;
    private final BookInfoMapperCustom bookInfoMapperCustom;
    private final IndexPageMapper indexPageMapper;
    private final IndexPageMapperCustom indexPageMapperCustom;
    private final NovelDetailPageMapperCustom novelDetailPageMapperCustom;
    private final NovelDetailPageMapper novelDetailPageMapper;
    @Autowired
    private SpiderService spiderService;

    /**
     * 搜索 小说内容
     *
     * @param keyword
     * @return
     */
    @Override
    public List<BookInfo> searchBookInfo(String keyword) throws IOException {
        String html = okHttpClientUtil.getAsHtmlStr(searchUrl+keyword);
        final List<BiQuGeSearchResult> bookList = parseNovelUtil.parseList(html, BiQuGeSearchResult.class);
        if (bookList==null) {
            return Collections.emptyList();
        }
        final List<BookInfo> collect = bookList.stream()
                .map(book -> {
                    return BookInfo.builder()
                            .bookIntroduction(book.getDescription())
                            .bookName(book.getNovelName())
                            .author(book.getAuthor())
                            .bookUrl(book.getNovelLink())
                            .imgUrl(book.getImg())
                            .build();

                })
                .collect(Collectors.toList());

        return collect;
    }

    /**
     * 下载文章链接
     *
     * @param bookUrl
     */
    @SneakyThrows
    @Override
    public List<IndexPage> getBookInfo(String bookUrl) {
        final String html = okHttpClientUtil.getAsHtmlStr(bookUrl);
        final List<BiQuGeTitleDTO> biQuGeTitleDTOS = parseNovelUtil.parseList(html, BiQuGeTitleDTO.class);

        if (biQuGeTitleDTOS==null || biQuGeTitleDTOS.size()==0) {
            return Collections.emptyList()
            ;
        }
        BiQuGeDetailBookInfoDTO detailInfo = (BiQuGeDetailBookInfoDTO) parseNovelUtil.parse(html,BiQuGeDetailBookInfoDTO.class).getResult();
        if (detailInfo==null) {
            return Collections.emptyList();
        }
        BookInfo bookInfo = bookInfoMapperCustom.selectByBookUrl(bookUrl);
        if (bookInfo==null) {
            bookInfo = BookInfo.builder()
                    .bookIntroduction(detailInfo.getBookDescription())
                    .bookUrl(bookUrl)
                    .author(detailInfo.getAuthorName())
                    .imgUrl(detailInfo.getImg())
                    .bookName(detailInfo.getBookTitle())
                    .alreadyFinished(false)
                    .build();
            ;
            bookInfoMapper.insertSelective(bookInfo);
        }


        // BookInfo finalBookInfo = bookInfo;
        BookInfo finalBookInfo = bookInfo;
        final List<IndexPage> collect = biQuGeTitleDTOS.stream()
                .map(item -> {
                    return IndexPage.builder()
                            .downloadUrl(item.getContentUrl())
                            .downloadTitle(item.getTitle())
                            .bookId(finalBookInfo.getBookId())
                            .build();
                }).collect(Collectors.toList());

        if (!bookInfo.getAlreadyFinished()) {
            spiderService.saveIndexPage(collect);
            //设置下载标记
            bookInfo.setAlreadyFinished(true);
            bookInfoMapper.updateByPrimaryKey(bookInfo);
        }
        return collect;
        // return null;
    }

    @SneakyThrows
    @Override
    public NovelDetailPage getNovelDetailPage(String downloadUrl) {
        IndexPage indexPage = indexPageMapperCustom.selectByDownloadUrl(downloadUrl);
        final Long indexId = indexPage.getIndexId();
        NovelDetailPage novelDetailPage = novelDetailPageMapperCustom.getNovelDetailPage(indexId);
        if (novelDetailPage==null) {
            final HtmlPageResult<BiQuGeNovelContentDTO> parse = parseNovelUtil.parse(okHttpClientUtil.getAsHtmlStr(downloadUrl), BiQuGeNovelContentDTO.class);
            final BiQuGeNovelContentDTO result = parse.getResult();
            novelDetailPage
                    = new NovelDetailPage();
            novelDetailPage.setBookDetail(result.getTxtContent());
            novelDetailPage.setIndexId(indexId);
            novelDetailPage.setDeleteStatus(false);
            novelDetailPageMapper.insertSelective(novelDetailPage);

        }
        return novelDetailPage;
    }

    @SneakyThrows
    @Override
    public NovelDetailPage getNovelDetailPage(Long indexId) {

        NovelDetailPage novelDetailPage = novelDetailPageMapperCustom.getNovelDetailPage(indexId);
        if (novelDetailPage==null) {
            IndexPage indexPage = indexPageMapper.selectByPrimaryKey(indexId);
            final HtmlPageResult<BiQuGeNovelContentDTO> parse = parseNovelUtil.parse(okHttpClientUtil.getAsHtmlStr(indexPage.getDownloadUrl()), BiQuGeNovelContentDTO.class);
            final BiQuGeNovelContentDTO result = parse.getResult();
            novelDetailPage
                    = new NovelDetailPage();
            novelDetailPage.setBookDetail(result.getTxtContent());
            novelDetailPage.setIndexId(indexId);
            novelDetailPage.setDeleteStatus(false);
            novelDetailPageMapper.insertSelective(novelDetailPage);

        }
        return novelDetailPage;

    }

    /**
     * 存储小说信息
     * @param list
     */
    @Async
    @Transactional(noRollbackFor = Exception.class)
    @Override
    public void saveIndexPage(List<IndexPage> list) {
        if (list!=null && list.size() > 0) {
            for (IndexPage page: list) {
                if (page.getDownloadUrl()!=null) {
                    final IndexPage indexPage = indexPageMapperCustom.selectByDownloadUrl(page.getDownloadUrl());
                    if(indexPage!=null) {
                        continue;
                    }
                    indexPageMapper.insertSelective(page);
                }
            }
        }
    }

}
