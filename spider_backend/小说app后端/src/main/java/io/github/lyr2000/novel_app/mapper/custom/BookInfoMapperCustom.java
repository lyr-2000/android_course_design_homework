package io.github.lyr2000.novel_app.mapper.custom;

import io.github.lyr2000.novel_app.pojo.po.BookInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author lyr
 * @create 2021/4/14 17:28
 */
public interface BookInfoMapperCustom {
    @Select("select * from t_book_info where book_url = #{url} limit 1")
    BookInfo selectByBookUrl(@Param("url") String url);

    @Select("select * from t_book_info ")
    List<BookInfo> selectBookInfo();
}
