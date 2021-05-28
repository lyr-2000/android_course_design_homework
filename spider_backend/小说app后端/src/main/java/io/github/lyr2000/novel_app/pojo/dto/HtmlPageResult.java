package io.github.lyr2000.novel_app.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import org.jsoup.nodes.Document;

/**
 * @Author lyr
 * @create 2021/4/13 22:30
 */
@Data
@Accessors(chain = true)
public class HtmlPageResult<T> {
    /**
     * 标题
     */
    private String title;
    private String url;
    /**
     * http 状态码
     */
    private int statusCode;
    /**
     * 解析出的内容
     */
    private T result;

    /**
     * jsoup 的内容
     */
    private Document document;

}
