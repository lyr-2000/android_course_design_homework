package io.github.lyr2000.novel_app.mapper;

import io.github.lyr2000.novel_app.pojo.po.IndexPage;

public interface IndexPageMapper {
    int insert(IndexPage record);

    int insertSelective(IndexPage record);

    IndexPage selectByPrimaryKey(Long indexId);

    int updateByPrimaryKeySelective(IndexPage record);

    int updateByPrimaryKey(IndexPage record);
}