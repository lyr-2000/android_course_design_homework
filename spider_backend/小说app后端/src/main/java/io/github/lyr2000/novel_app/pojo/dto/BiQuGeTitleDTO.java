package io.github.lyr2000.novel_app.pojo.dto;

import io.github.lyr2000.novel_app.core.DocCss;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 目录页
 *
 * @Author lyr
 * @create 2021/4/14 13:47
 */
//
@Data
@Accessors(chain = true)
@DocCss("#list dl dt:nth-of-type(2) ~dd")
public class BiQuGeTitleDTO {
    @DocCss(" a")
    private String title;
    @DocCss(value = "  a", attr = "href", prefix = "https://www.biqugeu.net/")
    private String contentUrl;

}
