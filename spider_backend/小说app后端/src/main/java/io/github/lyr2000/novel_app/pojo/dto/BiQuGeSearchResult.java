package io.github.lyr2000.novel_app.pojo.dto;

import io.github.lyr2000.novel_app.core.DocCss;
import lombok.Data;

/**
 * @Author lyr
 * @create 2021/4/14 12:52
 */
@DocCss(".item")
@Data

public class BiQuGeSearchResult {

    @DocCss(".item dl dt a")
    private String novelName;

    @DocCss(value = ".item dl dt a", attr = "href", prefix = "https://www.biqugeu.net/")
    private String novelLink;

    @DocCss(value = ".item .image a  img", attr = "src", prefix = "https://www.biqugeu.net/")
    private String img;

    @DocCss(".item dl dd")
    private String description;

    @DocCss(".item dl dt span")
    private String author;
}
