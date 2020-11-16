package com.jg.blog.service;

import com.jg.blog.pojo.About;
import com.jg.blog.utils.Page;

import java.util.List;

/**
 * author 老唐
 * time 2020-5-17
 * age:21
 *
 * @author adminstrator
 */
public interface AboutService {
    /**
     * 增加
     *
     * @param about
     */
    void save(About about);

    /**
     * 更新
     *
     * @param about
     */
    void update(About about);

    /**
     * 单查询
     *
     * @param id
     * @return
     */
    About getById(Integer id);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(Integer id);


    /**
     * 根据id阅读
     *
     * @param
     * @return
     */
    About read();

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
    Page<About> getByPage(Page<About> page);
}
