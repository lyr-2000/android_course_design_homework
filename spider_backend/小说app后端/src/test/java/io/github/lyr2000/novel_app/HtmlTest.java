package io.github.lyr2000.novel_app;

import io.github.lyr2000.novel_app.common.html.MapDfsUtil;
import io.github.lyr2000.novel_app.pojo.po.BookInfo;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

/**
 * @Author lyr
 * @create 2021/4/15 13:25
 */
public class HtmlTest {
    public static void main(String[] args) {
        File file = new File("D:\\ASUS\\Desktop\\my_demo\\server\\小说app后端\\src\\main\\resources\\html.yml");
        Yaml yaml = new Yaml();
        try(
                FileInputStream in = new FileInputStream(file);
                ) {
            final Map load = yaml.load(in);
            //System.out.println(load);
            System.out.println(MapDfsUtil.dfs(load, BookInfo.class));
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    // public void test

    @Test
    void spss() {
        String s = " method=post  <div ></div>";
        final int x = s.indexOf("<");
        System.out.println(x);
        System.out.println(s.substring(0,x) +">" + s.substring(x));
    }
}
