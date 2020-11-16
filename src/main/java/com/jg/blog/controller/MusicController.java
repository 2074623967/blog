package com.jg.blog.controller;

import com.jg.blog.enums.ResultEnum;
import com.jg.blog.pojo.Music;
import com.jg.blog.pojo.Result;
import com.jg.blog.service.MusicService;
import com.jg.blog.utils.Page;
import com.jg.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/music")
public class MusicController {
    @Autowired
    private MusicService musicService;

    /**
     * 增加
     *
     * @param music
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Music music) {
        musicService.save(music);
        return new Result<>("添加成功");

    }

    /**
     * 修改
     *
     * @param music
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody Music music) {
        musicService.update(music);
        return new Result<>("更新成功");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    public Result<Music> getById(@PathVariable Integer id) {
        Music music = musicService.getById(id);
        return new Result<>(music);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id) {
        musicService.deleteById(id);
        return new Result<>("删除成功");
    }

    /**
     * 启用
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/enabled/{id}", method = RequestMethod.PUT)
    public Result<Object> enabled(@PathVariable Integer id) {
        musicService.enabledById(id);
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
        musicService.disabledById(id);
        return new Result<>("未启用");
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<Music>> getByPage(@RequestBody Page<Music> page) {
        //得到排序方式
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNoneBlank(sortColumn)) {
            //如果排序不为空
            String[] sortColumns = {"artist", "created_time", "enabled"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = musicService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 前台查询
     *
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Result<List<Music>> getList() {
        List<Music> musicList = musicService.getList();
        return new Result<>(musicList);
    }
}
