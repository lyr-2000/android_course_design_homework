package io.github.lyr2000.novel_app.service.impl;

import io.github.lyr2000.novel_app.mapper.custom.NovelDetailPageMapperCustom;
import io.github.lyr2000.novel_app.pojo.po.NovelDetailPage;
import io.github.lyr2000.novel_app.service.NovelDetailPageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author lyr
 * @create 2021/4/17 14:15
 */
@Service
@AllArgsConstructor
public class NovelDetailServiceImpl implements NovelDetailPageService {
      final NovelDetailPageMapperCustom novelDetailPageMapperCustom;

     @Override
     public NovelDetailPage getNovel(Long indexId) {
          return novelDetailPageMapperCustom.getNovelDetailPage(indexId);
     }
}
