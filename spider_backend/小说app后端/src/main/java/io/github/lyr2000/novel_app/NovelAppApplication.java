package io.github.lyr2000.novel_app;

import com.lyr.mybatisjpaplugin.config.EnableFieldFillMybatisPlugin;
import com.lyr.mybatisjpaplugin.config.EnableMybatisXssPlugin;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//爬虫
@MapperScan("io.github.lyr2000.novel_app.mapper")
@SpringBootApplication
@EnableFieldFillMybatisPlugin
@EnableMybatisXssPlugin
public class NovelAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelAppApplication.class, args);
    }

}
