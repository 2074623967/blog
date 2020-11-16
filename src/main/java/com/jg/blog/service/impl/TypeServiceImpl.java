package com.jg.blog.service.impl;


import com.jg.blog.enums.StateEnums;
import com.jg.blog.exception.BlogException;
import com.jg.blog.mapper.TypeMapper;
import com.jg.blog.pojo.Type;
import com.jg.blog.service.TypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 帖子类型表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Service
@Slf4j
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public void save(Type type) {
        //查询分类是否存在
        Type t = this.typeMapper.getByName(type.getTypeName());
        if (t != null) {
            throw new BlogException("改分类已经存在");
        }
        typeMapper.insert(type);
    }

    /**
     * 后台查询所有
     *
     * @return
     */
    @Override
    public List<Type> getAll() {
        return typeMapper.getAll();
    }

    @Override
    public void update(Type type) {
        typeMapper.update(type);
    }

    @Override
    public void deleteById(Integer id) {
        typeMapper.deleteById(id);
    }

    /**
     * 前台查询所有
     *
     * @return
     */
    @Override
    public List<Type> getTypeList() {
        return typeMapper.getTypeList();
    }

    @Override
    public void enabledById(Integer id) {
        Type t = typeMapper.getById(id);
        t.setEnable(StateEnums.ENABLED.getCode());
        typeMapper.update(t);
    }

    @Override
    public void disabledById(Integer id) {
        Type t = typeMapper.getById(id);
        t.setEnable(StateEnums.NOT_ENABLE.getCode());
        typeMapper.update(t);
    }

    @Override
    public Type getById(Integer id) {
        return typeMapper.getById(id);
    }
}
