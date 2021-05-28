package io.github.lyr2000.novel_app;

import io.github.lyr2000.novel_app.pojo.dto.BiQuGeSearchResult;
import io.github.lyr2000.novel_app.pojo.dto.BiQuGeTitleDTO;
import io.github.lyr2000.novel_app.pojo.dto.HtmlPageResult;
import io.github.lyr2000.novel_app.util.OkHttpClientUtil;
import io.github.lyr2000.novel_app.util.ParseNovelUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;

/**
 * @Author lyr
 * @create 2021/4/13 21:01
 */
@SpringBootTest
public class GetNovelDetailTest {
    @Autowired
    private OkHttpClientUtil okHttpClientUtil;
    String url = "https://www.biquduu.com/133_133582/7602536.html";

    @Test
    void callit() throws IOException {
        System.out.println(okHttpClientUtil.getAsHtmlStr(url));
    }


    @Test
    void printIt() throws IOException {
        String url = "https://www.biquduu.com/133_133582/7602536.html";
        final Document document = Jsoup.connect(url).get();
        System.out.println(document.title());
        final Elements select = document.body()
                .select("#content");
        System.out.println(select);//打印 HTML 文本
        System.out.println(select.text());//打印文字内容
    }

    @Autowired
    private ParseNovelUtil parseNovelUtil;
    @Test
    void testParseUtil() throws IOException {
        final String asHtmlStr = okHttpClientUtil.getAsHtmlStr("https://www.biqugeu.net/searchbook.php?keyword=绝命诱惑");
        final HtmlPageResult parse = parseNovelUtil.parse(asHtmlStr, BiQuGeSearchResult.class);
        System.out.println(parse.getResult());


    }

    @Test
    void testParseUtillist() throws IOException {
        final String asHtmlStr = okHttpClientUtil.getAsHtmlStr("https://www.biqugeu.net/searchbook.php?keyword=逆天");
        final List list = parseNovelUtil.parseList(asHtmlStr, BiQuGeSearchResult.class);
        // System.out.println(parse.getResult());
        System.out.println(list);

    }

    @Test
    void printTitleList() throws IOException {
        // String url="https://www.biqugeu.net//0_583/";
        final List<BiQuGeTitleDTO> biQuGeTitleDTOS = parseNovelUtil.parseList(okHttpClientUtil.getAsHtmlStr("https://www.biqugeu.net//0_583/"), BiQuGeTitleDTO.class);
        System.out.println(biQuGeTitleDTOS);
    }
}
