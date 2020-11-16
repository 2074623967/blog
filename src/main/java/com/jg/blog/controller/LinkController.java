package com.jg.blog.controller;

import com.jg.blog.pojo.Link;
import com.jg.blog.pojo.Log;
import com.jg.blog.pojo.Result;
import com.jg.blog.pojo.Type;
import com.jg.blog.service.LinkService;
import com.jg.blog.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

/**
 * author 老唐
 * time 2020-5-17
 * age:21
 *
 * @author adminstrator
 */
@RestController
@RequestMapping("link")
public class LinkController {
    @Autowired
    private LinkService linkService;

    /**
     * 添加友情链接
     *
     * @param link
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Link link) {
        linkService.save(link);
        return new Result<>("添加成功");
    }

    /**
     * 更新友情链接
     *
     * @param link
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody Link link) {
        linkService.update(link);
        return new Result<>("更新成功");
    }

    /**
     * 删除友情链接
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public Result<Object> delete(@PathVariable Integer id) {
        linkService.deleteById(id);
        return new Result<>("删除成功");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    public Result<Object> getById(@PathVariable Integer id) {
        Link link = linkService.getById(id);
        return new Result<>(link);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<List<Link>> list() {
        List<Link> list = linkService.getAll();
        return new Result<>(list);
    }
}
