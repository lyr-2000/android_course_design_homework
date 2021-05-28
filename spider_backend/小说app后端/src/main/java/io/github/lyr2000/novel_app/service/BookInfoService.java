package io.github.lyr2000.novel_app.service;

import io.github.lyr2000.common.dto.PageParam;
import io.github.lyr2000.novel_app.pojo.po.BookInfo;

import java.util.List;

/**
 * @Author lyr
 * @create 2021/4/13 20:38
 */
public interface BookInfoService {
    /**
     * 插入小说内容
     * @param bookInfoList
     */
    void insertList(List<BookInfo> bookInfoList);

    /**
     * 展示书籍信息
     * @param pageParam
     * @return
     */
    List<BookInfo> listAllBook(PageParam pageParam);
}
