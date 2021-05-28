package io.github.lyr2000.novel_app.service.impl;

import io.github.lyr2000.novel_app.pojo.po.IndexPage;
import io.github.lyr2000.novel_app.service.SpiderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author lyr
 * @create 2021/4/14 17:34
 */
@SpringBootTest
public class SpringbootTest_SpiderServiceImplTest {
    @Autowired
    private SpiderService spiderService;

    @Test
    void testGetHtml() throws InterruptedException {
        final List<IndexPage> bookInfo = spiderService.getBookInfo("https://www.biqugeu.net/0_583/");
        System.out.println(bookInfo);

        Thread.sleep(1000*120);
    }

}
