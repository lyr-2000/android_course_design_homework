package io.github.lyr2000.novel_app.service.impl;

import io.github.lyr2000.novel_app.pojo.dto.BiQuGeDetailBookInfoDTO;
import io.github.lyr2000.novel_app.service.SpiderService;
import io.github.lyr2000.novel_app.util.OkHttpClientUtil;
import io.github.lyr2000.novel_app.util.ParseNovelUtil;
import okhttp3.OkHttpClient;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author lyr
 * @create 2021/4/14 16:06
 */
class SpiderServiceImplTest {
    // @Mock
    private SpiderService spiderService;
    private ParseNovelUtil parseNovelUtil;
    private OkHttpClient okHttpClient;
    private OkHttpClientUtil okHttpClientUtil;

    @BeforeEach
    void setUp() {
        okHttpClient = new OkHttpClient();
        okHttpClientUtil = new OkHttpClientUtil(okHttpClient);
        // spiderService = new SpiderServiceImpl(okHttpClientUtil,new ParseNovelUtil(),null,null,null,null);
        parseNovelUtil  = new ParseNovelUtil();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void searchBookInfo() throws IOException {
        System.out.println(spiderService.searchBookInfo("逆天"));
    }
    @Test
    void searchBookInfo2() throws IOException {
        System.out.println(spiderService.searchBookInfo("绝命诱惑"));
    }

    @Test
    void printDetailInfo() throws IOException {
        System.out.println(
               parseNovelUtil.parse(
                       okHttpClientUtil.getAsHtmlStr("https://www.biqugeu.net/0_583/")
                       ,BiQuGeDetailBookInfoDTO.class).getResult()
        );
    }
}