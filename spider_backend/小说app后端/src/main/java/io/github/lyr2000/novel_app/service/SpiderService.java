package io.github.lyr2000.novel_app.service;

import io.github.lyr2000.novel_app.pojo.po.BookInfo;
import io.github.lyr2000.novel_app.pojo.po.IndexPage;
import io.github.lyr2000.novel_app.pojo.po.NovelDetailPage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @Author lyr
 * @create 2021/4/13 20:39
 */
public interface SpiderService {

    /**
     * 搜索 小说内容
     * @param keyword
     * @return
     */
    List<BookInfo> searchBookInfo(String keyword) throws IOException;


    /**
     * 下载文章链接
     * @param bookUrl
     */
    List<IndexPage> getBookInfo(String bookUrl);

    NovelDetailPage getNovelDetailPage(String downloadUrl);
    NovelDetailPage getNovelDetailPage(Long indexId);


    @Async
    @Transactional(noRollbackFor = Exception.class)
    void saveIndexPage(List<IndexPage> list);
}
