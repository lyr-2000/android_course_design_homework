package io.github.lyr2000.novel_app.service;

import io.github.lyr2000.common.dto.PageParam;
import io.github.lyr2000.novel_app.pojo.po.IndexPage;

import java.util.List;

/**
 * @Author lyr
 * @create 2021/4/13 20:39
 */
public interface IndexPageService {
    /**
     * 根据 bookId 获取标题信息
     * @param bookId
     * @return
     */
    List<IndexPage> listIndexPageByBookId(Integer bookId, PageParam pageParam);
}
