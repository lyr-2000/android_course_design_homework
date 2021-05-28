package io.github.lyr2000.novel_app.service;

import io.github.lyr2000.novel_app.pojo.po.NovelDetailPage;

/**
 * @Author lyr
 * @create 2021/4/13 20:39
 */
public interface NovelDetailPageService {
    NovelDetailPage getNovel(Long indexId);
}
