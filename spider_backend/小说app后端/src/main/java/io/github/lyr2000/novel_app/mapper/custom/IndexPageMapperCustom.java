package io.github.lyr2000.novel_app.mapper.custom;

import io.github.lyr2000.novel_app.pojo.po.IndexPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author lyr
 * @create 2021/4/14 18:09
 */
public interface IndexPageMapperCustom {

    /**
     * 根据下载链接获取
     * @param url
     * @return
     */
    @Select("select * from t_index_page where download_url = #{url}  limit 1")
    IndexPage selectByDownloadUrl(@Param("url") String url);

    @Select("select * from t_index_page  where book_id = #{id}")
    List<IndexPage> listIndexPage(@Param("id") Integer bookId);
}
