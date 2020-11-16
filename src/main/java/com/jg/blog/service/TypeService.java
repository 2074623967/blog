package com.jg.blog.service;


import afu.org.checkerframework.checker.igj.qual.I;
import com.jg.blog.pojo.Type;

import java.util.List;

/**
 * <p>
 * 帖子类型表服务层接口
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
public interface TypeService {

    /**
     * 保存
     *
     * @param type
     */
    void save(Type type);

    /**
     * 查询所有类型
     *
     * @return
     */
    List<Type> getAll();

    /**
     * 更新类型
     *
     * @param type
     */
    void update(Type type);

    /**
     * 删
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 前台查询所有
     *
     * @return
     */
    List<Type> getTypeList();

    /**
     * 根据id启用
     *
     * @param id
     */
    void enabledById(Integer id);

    /**
     * 根据id弃用
     *
     * @param id
     */
    void disabledById(Integer id);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    Type getById(Integer id);
}
