package io.github.lyr2000.novel_app.controller;

import io.github.lyr2000.common.dto.PageParam;
import io.github.lyr2000.novel_app.pojo.po.BookInfo;
import io.github.lyr2000.novel_app.pojo.po.IndexPage;
import io.github.lyr2000.novel_app.pojo.po.NovelDetailPage;
import io.github.lyr2000.novel_app.service.BookInfoService;
import io.github.lyr2000.novel_app.service.SpiderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @Author lyr
 * @create 2021/4/13 20:44
 */
@Slf4j
@AllArgsConstructor
@Controller
public class NovelController {

    final SpiderService spiderService;
    final BookInfoService bookInfoService;

    @ExceptionHandler(Exception.class)
    public ModelAndView getEx(Exception e) {
      log.error("发生异常了 {}", e.getMessage());
      return  new ModelAndView("error");
    }


    @GetMapping({"/","/index.html"})
    public String index(Model model, HttpServletRequest req) {
        model.addAttribute("list",bookInfoService.listAllBook(PageParam.of(req,5)));
        return "/index";

    }

    @GetMapping("/download")
    public String indexPage() {
        return "/download";
    }


    @RequestMapping("/search_novel")
    public String searchNovel(@RequestParam(required = false) String keyword, Model model) throws IOException {
        if (keyword!=null) {
            final List<BookInfo> list = spiderService.searchBookInfo(keyword);
            model.addAttribute("list",list);
        }
        return "download";
    }


    @RequestMapping("/novel_title_list")
    public String downloadBook(@RequestParam String downloadUrl,Model model) {
        final List<IndexPage> bookInfo = spiderService.getBookInfo(downloadUrl);
        model.addAttribute("list",bookInfo);
        return "novel_page";
    }

    @RequestMapping("/novel_detail")
    public String getNovelDetail(@RequestParam String downloadUrl , Model model) {
        final NovelDetailPage detail = spiderService.getNovelDetailPage(downloadUrl);
        model.addAttribute("detail",detail);
        return "/novel_detail";
    }


}
