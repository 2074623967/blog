package com.jg.blog.controller;

import com.jg.blog.enums.ResultEnum;
import com.jg.blog.pojo.*;
import com.jg.blog.service.CommentService;
import com.jg.blog.utils.Page;
import com.jg.blog.utils.ShiroUtils;
import com.jg.blog.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * author 老唐
 * time 2020-5-17
 * age:21
 *
 * @author adminstrator
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 添加
     *
     * @param comment
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody Comment comment) {
        User user = (User) ShiroUtils.getLoginUser();
        comment.setCommentUser(user.getUserId());
        if (StringUtils.isBlank(comment.getCommentBlog())) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "参数id不能为空");
        }
        commentService.save(comment);
        return new Result<>("评论成功");
    }

    /**
     * 查询当前博客的评论
     *
     * @param blogId
     * @return
     */
    @RequestMapping(value = "/getByBlog/{blogId}", method = RequestMethod.GET)
    public Result<List<Comment>> getByBlog(@PathVariable String blogId) {
        List<Comment> commentList = commentService.getByBlog(blogId);
        return new Result<>(commentList);
    }

    /**
     * 根据id删除,后台使用
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public Result<Object> deleteById(@PathVariable String id) {
        commentService.deleteById(id);
        return new Result<>("删除成功");
    }

    /**
     * 评论
     *
     * @param commentGoods
     * @return
     */
    @RequestMapping(value = "/good", method = RequestMethod.POST)
    public Result<Object> good(@RequestBody CommentGoods commentGoods) {
        if (StringUtils.isBlank(commentGoods.getCommentId())) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "评论id不能为空");
        }
        commentService.goodByCommentIdAndUser(commentGoods);
        return new Result<>("点赞成功");
    }

    /**
     * 根据评论id和当前登录用户查询点赞记录
     *
     * @param blogId
     * @return
     */
    @RequestMapping(value = "/getGood/{blogId}", method = RequestMethod.GET)
    public Result<Integer> getComment(@PathVariable String blogId) {
        int count = commentService.getGoodsCount(blogId);
        return new Result<>(count);
    }

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    public Result<Page<Comment>> getList(@RequestBody Page<Comment> page) {
        page = commentService.getByPage(page);
        return new Result<>(page);
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<Comment>> getByPage(@RequestBody Page<Comment> page) {
        page = commentService.getByPageBack(page);
        return new Result<>(page);
    }
}
