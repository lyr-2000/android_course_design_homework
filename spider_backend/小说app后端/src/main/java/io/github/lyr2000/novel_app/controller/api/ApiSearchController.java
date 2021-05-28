package io.github.lyr2000.novel_app.controller.api;

import io.github.lyr2000.common.dto.R;
import io.github.lyr2000.common.dto.Result;
import io.github.lyr2000.novel_app.pojo.po.BookInfo;
import io.github.lyr2000.novel_app.pojo.po.IndexPage;
import io.github.lyr2000.novel_app.service.SpiderService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @Author lyr
 * @create 2021/4/17 16:16
 */
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/")
@RestController
public class ApiSearchController {
    final SpiderService spiderService;

    @RequestMapping("/search_novel")
    public R searchNovel(@RequestParam(defaultValue = "") String keyword ) throws IOException {

        final List<BookInfo> list = spiderService.searchBookInfo(keyword);
        return R.res()
                .put("list",list);


    }

    @RequestMapping("/download")
    public Result download(@RequestParam  String url) {
        final List<IndexPage> bookInfo = spiderService.getBookInfo(url);
        if (bookInfo==null || bookInfo.isEmpty()) {
            return R.fail();
        }
        final Long bookId = bookInfo.get(0).getBookId();
        return R.res()
                .put("bookId", bookId)
                .end();
    }
}
