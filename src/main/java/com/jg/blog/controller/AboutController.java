package com.jg.blog.controller;

import com.jg.blog.enums.ResultEnum;
import com.jg.blog.pojo.*;
import com.jg.blog.service.AboutService;
import com.jg.blog.utils.Page;
import com.jg.blog.utils.StringUtils;
import com.jg.blog.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RelationSupport;
import java.util.Arrays;
import java.util.List;

/**
 * author 老唐
 * time 2020-5-17
 * age:21
 *
 * @author adminstrator
 */
@RestController
@RequestMapping("/about")
public class AboutController {
    @Autowired
    private AboutService aboutService;

    /**
     * 增加
     *
     * @param about
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody About about) {
        aboutService.save(about);
        return new Result<>("添加成功");

    }

    /**
     * 修改
     *
     * @param about
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody About about) {
        aboutService.update(about);
        return new Result<>("更新成功");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    public Result<About> getById(@PathVariable Integer id) {
        About about = aboutService.getById(id);
        return new Result<>(about);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id) {
        aboutService.deleteById(id);
        return new Result<>("删除成功");
    }

    /**
     * 查询
     *
     * @return
     */
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public Result<About> read() {
        About about = aboutService.read();
        return new Result<>(about);
    }

    /**
     * 启用
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/enabled/{id}", method = RequestMethod.PUT)
    public Result<Object> enabled(@PathVariable Integer id) {
        aboutService.enabledById(id);
        return new Result<>("已启用");
    }

    /**
     * 弃用
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/disabled/{id}", method = RequestMethod.PUT)
    public Result<Object> disabled(@PathVariable Integer id) {
        aboutService.disabledById(id);
        return new Result<>("未启用");
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<About>> getByPage(@RequestBody Page<About> page) {
        //得到排序方式
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNoneBlank(sortColumn)) {
            //如果排序不为空
            String[] sortColumns = {"about_read", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = aboutService.getByPage(page);
        return new Result<>(page);
    }
}
