package com.jg.blog.mapper;

import afu.org.checkerframework.checker.igj.qual.I;
import com.jg.blog.pojo.Type;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.stereotype.Component;

import java.security.PrivateKey;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * <p>
 * 帖子类型表Mapper
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Component
public interface TypeMapper {
    /**
     * 根据id启用
     * @param id
     */

    /**
     * 查询
     */
    Type getByName(String typeName);

    /**
     * 增加
     *
     * @param type
     */
    public void insert(Type type);

    /**
     * 更新
     *
     * @param type
     */
    public void update(Type type);

    /**
     * 根据id删除
     *
     * @param id
     */
    public void deleteById(Integer id);

    /**
     * 全查询
     *
     * @return
     */
    List<Type> getAll();

    /***
     * 前台查询所有
     * @return
     */
    List<Type> getTypeList();

    /**
     * 根据id进行查询
     *
     * @param id
     * @return
     */
    Type getById(Integer id);
}
