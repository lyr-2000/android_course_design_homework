package io.github.lyr2000.novel_app.service.impl;

import com.github.pagehelper.PageHelper;
import io.github.lyr2000.common.dto.PageParam;
import io.github.lyr2000.novel_app.mapper.IndexPageMapper;
import io.github.lyr2000.novel_app.mapper.custom.IndexPageMapperCustom;
import io.github.lyr2000.novel_app.pojo.po.IndexPage;
import io.github.lyr2000.novel_app.service.IndexPageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lyr
 * @create 2021/4/17 13:09
 */
@Service
@AllArgsConstructor
public class IndexPageServiceImpl implements IndexPageService {
    final IndexPageMapper indexPageMapper;
    final IndexPageMapperCustom indexPageMapperCustom;

    /**
     * 根据 bookId 获取标题信息
     *
     * @param bookId
     * @return
     */
    @Override
    public List<IndexPage> listIndexPageByBookId(Integer bookId, PageParam pageParam) {

        PageHelper.startPage(pageParam);
        return indexPageMapperCustom.listIndexPage(bookId);
    }
}
