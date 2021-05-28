package io.github.lyr2000.novel_app.core;

import java.lang.annotation.*;

/**
 * @Author lyr
 * @create 2021/4/13 20:55
 */
@Target({ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DocCss {

    /**
     * @return css 选择器
     */
    String value();
    Options options() default Options.PlainText;

    /**
     * 结果前缀
     * @return
     */
    String prefix() default "";

    /**
     * @return 属性选择器
     */
    String attr() default "";


    public static enum Options{
        PlainText,
        HtmlText
    }
}
