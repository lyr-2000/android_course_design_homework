package io.github.lyr2000.novel_app.service.impl;

import com.github.pagehelper.PageHelper;
import io.github.lyr2000.common.dto.PageParam;
import io.github.lyr2000.novel_app.mapper.BookInfoMapper;
import io.github.lyr2000.novel_app.mapper.custom.BookInfoMapperCustom;
import io.github.lyr2000.novel_app.pojo.po.BookInfo;
import io.github.lyr2000.novel_app.service.BookInfoService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author lyr
 * @create 2021/4/14 16:57
 */
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Service
public class BookInfoServiceImpl implements BookInfoService {
    private final BookInfoMapper bookInfoMapper;
    private final BookInfoMapperCustom bookInfoMapperCustom;
    /**
     * 插入小说内容
     *
     * @param bookInfoList
     */
    @Override
    @Async
    public void insertList(List<BookInfo> bookInfoList) {
        for (BookInfo bookInfo: bookInfoList) {
            bookInfoMapper.insertSelective(bookInfo);
        }
    }

    /**
     * 展示书籍信息
     *
     * @param pageParam
     * @return
     */
    @Override
    public List<BookInfo> listAllBook(PageParam pageParam) {
        PageHelper.startPage(pageParam);
        return bookInfoMapperCustom.selectBookInfo();
    }
}
