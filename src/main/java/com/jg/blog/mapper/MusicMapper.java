package com.jg.blog.mapper;


import com.jg.blog.pojo.Music;
import com.jg.blog.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 接口访问日志表Mapper
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Component
public interface MusicMapper {
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
     * 查询
     *
     * @param id
     * @return
     */
    Music getById(Integer id);

    /**
     * 删除
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    List<Music> getByPage(Page<Music> page);

    /**
     * 查询总数
     *
     * @param page
     * @return
     */
    int getCountByPage(Page<Music> page);

    /**
     * 前台查询
     *
     * @return
     */
    List<Music> getList();
}
