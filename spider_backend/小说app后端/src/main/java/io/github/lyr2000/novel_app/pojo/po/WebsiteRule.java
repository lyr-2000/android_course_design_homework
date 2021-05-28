package io.github.lyr2000.novel_app.pojo.po;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lyr.mybatisjpaplugin.annotation.CreateTime;
import com.lyr.mybatisjpaplugin.annotation.UpdateTime;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WebsiteRule implements Serializable {
    private Long websiteId;

    /**
     * 网站名字
     */
    private String websiteName;

    /**
     * 网站地址
     */
    private String websiteUrl;

    /**
     * 逻辑删除
     */
    @JsonIgnore
    private Boolean deleteStatus;

    /**
     * 创建时间
     */
    @CreateTime
    private Date gmtCreate;

    /**
     * 更新时间
     */
    @UpdateTime
    private Date gmtModified;

    /**
     * 搜索 url
     */
    private String searchUrl;

    /**
     * 内容选择器
     */
    private String detailSelector;

    /**
     * 小说名字选择器
     */
    private String bookNameSelector;

    /**
     * 小说标题选择器
     */
    private String bookTitleSelector;

    private static final long serialVersionUID = 1L;
}