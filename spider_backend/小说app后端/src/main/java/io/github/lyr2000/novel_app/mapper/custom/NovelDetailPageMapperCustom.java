package io.github.lyr2000.novel_app.mapper.custom;

import io.github.lyr2000.novel_app.pojo.po.NovelDetailPage;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @Author lyr
 * @create 2021/4/14 23:27
 */
public interface NovelDetailPageMapperCustom {
    /**
     * 获取文章信息
     * @param indexId
     * @return
     */
    @Select("select * from t_novel_detail_page where index_id = #{indexId} limit 1")
    NovelDetailPage getNovelDetailPage(Long indexId);
}
