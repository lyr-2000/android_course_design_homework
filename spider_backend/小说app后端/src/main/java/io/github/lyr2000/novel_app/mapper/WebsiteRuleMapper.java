package io.github.lyr2000.novel_app.mapper;

import io.github.lyr2000.novel_app.pojo.po.WebsiteRule;

public interface WebsiteRuleMapper {
    int insert(WebsiteRule record);

    int insertSelective(WebsiteRule record);

    WebsiteRule selectByPrimaryKey(Long websiteId);

    int updateByPrimaryKeySelective(WebsiteRule record);

    int updateByPrimaryKey(WebsiteRule record);
}