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
public class BookInfo implements Serializable {
    /**
     * 小说ID
     */
    private Long bookId;

    /**
     * 网页ID
     */
    private Long websiteId;

    /**
     * 书籍名字
     */
    private String bookName;

    /**
     * 下载网页的Id
     */
    private Long fromWebsiteId;

    /**
     * 逻辑删除
     */
    @JsonIgnore
    private Boolean deleteStatus;

    /**
     * 作者名字
     */
    private String author;

    /**
     * 小说下载地址
     */
    private String bookUrl;

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
     * 网页图片
     */
    private String imgUrl;

    /**
     * 是否下载完成
     */
    private Boolean alreadyFinished;

    /**
     * 内容介绍
     */
    private String bookIntroduction;

    private static final long serialVersionUID = 1L;
}