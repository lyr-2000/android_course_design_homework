package io.github.lyr2000.novel_app.util;

import cn.hutool.core.util.StrUtil;
import io.github.lyr2000.novel_app.core.DocCss;
import io.github.lyr2000.novel_app.pojo.dto.HtmlPageResult;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author lyr
 * @create 2021/4/13 20:49
 */
@Slf4j
@Component
public class ParseNovelUtil {
    // @Resource
    // private OkHttpClientUtil okHttpClientUtil;


    @SneakyThrows
    @Deprecated
    public <T> T textToObject(String text, @NonNull Class<T> clazz) {
        final Document parse = Jsoup.parse(text);
        final Field[] fields = clazz.getDeclaredFields();
        final Object o = clazz.getConstructor().newInstance();
        for (Field field : fields) {
            final DocCss doc = field.getAnnotation(DocCss.class);
            if (doc != null) {
                final String css = doc.value();
                final Elements el = parse.select(css);
                final String elText = doc.options() == DocCss.Options.PlainText ? el.text() : el.html();
                if (String.class.isAssignableFrom(field.getType())) {
                    field.set(o, elText);
                }
            }
        }
        return (T) o;
    }

    @SneakyThrows
    public <T> HtmlPageResult<T> parse(String text, Class<T> clazz) {
        HtmlPageResult res = new HtmlPageResult();
        final Document parse = Jsoup.parse(text);
        final Field[] fields = clazz.getDeclaredFields();
        final Object o = clazz.getConstructor().newInstance();
        for (Field field : fields) {
            field.setAccessible(true);
            final DocCss doc = field.getAnnotation(DocCss.class);
            if (doc != null) {
                final String css = doc.value();
                final Elements el = parse.select(css);
                if (StrUtil.isBlank(doc.attr())) {
                    final String elText = doc.options() == DocCss.Options.PlainText ? el.text() : el.html();
                    if (String.class.isAssignableFrom(field.getType())) {
                        field.set(o, doc.prefix() + elText);
                    }
                } else {
                    final String elText = el.attr(doc.attr());
                    if (String.class.isAssignableFrom(field.getType())) {
                        field.set(o, doc.prefix() + elText);
                    }
                }
            }
        }
        //填充返回内容
        res.setDocument(parse)
                .setResult(o)
                .setTitle(parse.title())
        ;
        return res;
    }

    @SneakyThrows
    public <T> List<T> parseList(String source, Class<T> clazz) {
        final Document doc = Jsoup.parse(source);

        DocCss listContainer = clazz.getAnnotation(DocCss.class);
        if (listContainer == null) {
            log.info("无法定位");
            return (List<T>) Arrays.asList(parse(source, clazz).getResult());
        } else {
            final Elements listEL = doc.select(listContainer.value());
            List<T> result = new ArrayList<>(listEL.size());
            //System.out.println(result);
            for (Element el : listEL) {
                T obj = clazz.getConstructor().newInstance();
                //循环解析
                for (Field field : clazz.getDeclaredFields()) {
                    field.setAccessible(true);
                    DocCss flag = field.getAnnotation(DocCss.class);
                    if (flag != null) {
                        final String css = flag.value();
                        final Elements element = el.select(css);
                        if (element == null) {
                            continue;
                        }
                        if (StrUtil.isBlank(flag.attr())) {
                            //内容
                            String content = flag.options() == DocCss.Options.PlainText ? element.text() : element.html();
                            field.set(obj, flag.prefix() + content);
                        } else {
                            //属性
                            String content = element.attr(flag.attr());
                            field.set(obj, flag.prefix() + content);

                        }
                    }
                }
                result.add(obj);
            }


            return result;
            //doc = doc.select(listContainer.value());
        }
    }

    // @Resource
    // private OkHttpClient okHttpClient;


}
