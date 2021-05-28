package io.github.lyr2000.novel_app.controller.api;

import io.github.lyr2000.common.dto.PageParam;
import io.github.lyr2000.common.dto.R;
import io.github.lyr2000.novel_app.service.BookInfoService;
import io.github.lyr2000.novel_app.service.IndexPageService;
import io.github.lyr2000.novel_app.service.NovelDetailPageService;
import io.github.lyr2000.novel_app.service.SpiderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author lyr
 * @create 2021/4/16 21:18
 */
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/")
@RestController
public class ApiV1Controller {
    final BookInfoService bookInfoService;
    final IndexPageService indexPageService;
    final NovelDetailPageService novelDetailPageService;
    final SpiderService spiderService;
    @GetMapping("/book_list_current")
    public R bookList(HttpServletRequest req) {
        return R.res()
                .withPage("books",bookInfoService.listAllBook(PageParam.of(req,10)))
                ;
    }

    @GetMapping("/book_title")
    public R getBookTitle(@RequestParam Integer bookId,HttpServletRequest req) {

        return R.res()
                .withPage("titleList",indexPageService.listIndexPageByBookId(bookId,PageParam.of(req,50)) )
                ;

    }

    @GetMapping("/book_detail")
    public R getDetail(@RequestParam Long indexId) {
        // novelDetailPageService.
        return R.res()
                .put("result",spiderService.getNovelDetailPage(indexId));
    }
}
