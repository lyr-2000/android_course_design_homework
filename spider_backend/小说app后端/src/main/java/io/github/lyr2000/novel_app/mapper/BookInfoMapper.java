package io.github.lyr2000.novel_app.mapper;

import io.github.lyr2000.novel_app.pojo.po.BookInfo;

public interface BookInfoMapper {
    int insert(BookInfo record);

    int insertSelective(BookInfo record);

    BookInfo selectByPrimaryKey(Long bookId);

    int updateByPrimaryKeySelective(BookInfo record);

    int updateByPrimaryKeyWithBLOBs(BookInfo record);

    int updateByPrimaryKey(BookInfo record);
}