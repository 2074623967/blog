package com.jg.blog.service;

import com.jg.blog.pojo.About;
import com.jg.blog.pojo.Music;
import com.jg.blog.utils.Page;

import java.util.List;

/**
 * author 老唐
 * time 2020-5-17
 * age:21
 *
 * @author adminstrator
 */
public interface MusicService {
    /**
     * 保存
     *
     * @param music
     */
    void save(Music music);

    /**
     * 更新
     *
     * @param music
     */
    void update(Music music);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Music getById(Integer id);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 启用
     *
     * @param id
     */
    void enabledById(Integer id);

    /**
     * 弃用
     *
     * @param id
     */
    void disabledById(Integer id);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    Page<Music> getByPage(Page<Music> page);

    /**
     * 前台查询
     *
     * @return
     */
    List<Music> getList();
}
