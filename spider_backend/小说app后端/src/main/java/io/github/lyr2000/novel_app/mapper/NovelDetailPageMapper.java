package io.github.lyr2000.novel_app.mapper;

import io.github.lyr2000.novel_app.pojo.po.NovelDetailPage;

public interface NovelDetailPageMapper {
    int insert(NovelDetailPage record);

    int insertSelective(NovelDetailPage record);

    NovelDetailPage selectByPrimaryKey(Long detailId);

    int updateByPrimaryKeySelective(NovelDetailPage record);

    int updateByPrimaryKeyWithBLOBs(NovelDetailPage record);

    int updateByPrimaryKey(NovelDetailPage record);
}