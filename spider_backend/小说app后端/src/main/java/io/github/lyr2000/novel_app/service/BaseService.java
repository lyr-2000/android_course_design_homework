package io.github.lyr2000.novel_app.service;

import io.github.lyr2000.common.dto.PageParam;

import java.util.List;

/**
 * @Author lyr
 * @create 2021/4/13 20:16
 */
public interface BaseService<T,Id> {
    /**
     * 根据Id 获取
     * @param id
     * @return
     */
    T getById(Id id);

    /**
     * 批量获取数据
     * @param searchInfo
     * @return
     */
    List<T> getByList(T searchInfo, PageParam pageParam);


    /**
     * 更新或者 插入
     * @param item
     */
    void updateOrInsert(T item);


}
