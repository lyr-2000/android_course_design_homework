package io.github.lyr2000.novel_app.pojo.dto;

import io.github.lyr2000.novel_app.core.DocCss;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @Author lyr
 * @create 2021/4/14 17:09
 */
@Data
@Accessors(chain = true)
public class BiQuGeDetailBookInfoDTO {
    /**
     * 小说标题
     */
    @DocCss("#info h1")
    private String bookTitle;
    @DocCss("#info p:nth-of-type(1)")
    private String authorName;
    /**
     * 小说描述
     */
    @DocCss("#intro")
    private String bookDescription;

    /**
     * 小说更新时间
     */
    @DocCss("#info p:nth-of-type(3)")
    private String bookUpdateTime;

    /**
     * 最新章节
     */
    @DocCss("#info p:nth-child(4)")
    private String theLastChapter;

    @DocCss(value = "#fmimg img",attr = "src",prefix = "https://")
    private String img;


}
