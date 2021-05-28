package io.github.lyr2000.novel_app.pojo.dto;

import io.github.lyr2000.novel_app.core.DocCss;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 笔趣阁的内容
 * @Author lyr
 * @create 2021/4/13 20:53
 */
@Data
@Accessors(chain = true)
public class BiQuGeNovelContentDTO {
    @DocCss(".bookname h1")
    private String title;

    private String url;
    // // @DocCss(value = "content",options = DocCss.Options.HtmlText)
    // private String htmlContent;


    @DocCss(value = "#content")
    private String txtContent;

    @DocCss(".next")
    private String nextPageUrl;
    @DocCss(".pre")
    private String prevPageUrl;


}
