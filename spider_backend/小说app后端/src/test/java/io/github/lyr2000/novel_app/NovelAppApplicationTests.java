package io.github.lyr2000.novel_app;

import com.lyr.mybatisjpaplugin.util.MybatisCodeGenerator;
import io.github.lyr2000.novel_app.mapper.BookInfoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NovelAppApplicationTests {
    @Autowired
    private BookInfoMapper bookInfoMapper;

    @Test
    void ll() {

    }

    @Test
    void contextLoads() {
        new MybatisCodeGenerator()
        {
            @Override
            public String getAbsolutePathGeneratorConfig_xml() {
                return "D:\\ASUS\\Desktop\\my_demo\\server\\小说app后端\\src\\main\\resources\\generatorConfig.xml";
            }
        }.xml_make();
    }

}
