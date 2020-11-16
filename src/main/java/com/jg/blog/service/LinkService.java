package com.jg.blog.service;

import com.jg.blog.pojo.Link;
import com.jg.blog.pojo.Type;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * author 老唐
 * time 2020-5-17
 * age:21
 *
 * @author adminstrator
 */
@Component
public interface LinkService {
    /**
     * 增加
     *
     * @param link
     */
    void save(Link link);

    /**
     * 修改
     *
     * @param link
     */
    void update(Link link);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Link getById(Integer id);

    /**
     * 全查询
     *
     * @return
     */
    List<Link> getAll();
}
