package com.jg.blog.controller;

import afu.org.checkerframework.checker.oigj.qual.O;
import com.jg.blog.mapper.TypeMapper;
import com.jg.blog.pojo.Result;
import com.jg.blog.pojo.Type;
import com.jg.blog.service.TypeService;
import org.apache.poi.ss.formula.functions.T;
import org.checkerframework.checker.units.qual.A;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author 老唐
 * time 2020-5-17
 * age:21
 **/
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;

    /**
     * 添加类型
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Type type) {
        typeService.save(type);
        return new Result<>("添加成功");
    }

    /***
     * 后台查询所有类型
     * @return
     */
    @RequestMapping(value = "/listBack", method = RequestMethod.GET)
    public Result<List<Type>> list() {
        List<Type> list = typeService.getAll();
        return new Result<>(list);
    }

    /***
     * 前台查询所有类型
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Result<List<Type>> getList() {
        List<Type> list = typeService.getTypeList();
        return new Result<>(list);
    }

    /**
     * 更新
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody Type type) {
        typeService.update(type);
        return new Result<>("更新成功");
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    public Result<Object> getById(@PathVariable Integer id) {
        Type type = typeService.getById(id);
        return new Result<>(type);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Result<Object> delete(@PathVariable Integer id) {
        typeService.deleteById(id);
        return new Result<>("删除成功");
    }

    @RequestMapping(value = "/enabled/{id}", method = RequestMethod.PUT)
    public Result<Object> enabled(@PathVariable Integer id) {
        typeService.enabledById(id);
        return new Result<>("已启用");
    }

    @RequestMapping(value = "/disabled/{id}", method = RequestMethod.PUT)
    public Result<Object> disabled(@PathVariable Integer id) {
        typeService.disabledById(id);
        return new Result<>("未启用");
    }
}
