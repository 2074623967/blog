package com.jg.blog.service;


import com.jg.blog.pojo.Comment;
import com.jg.blog.pojo.CommentGoods;
import com.jg.blog.utils.Page;

import java.util.List;

/**
 * <p>
 * 评论表服务层接口
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
public interface CommentService {

    /**
     * 保存
     *
     * @param comment
     */
    void save(Comment comment);

    /**
     * 查询当前博客的评论
     *
     * @param blogId
     * @return
     */
    List<Comment> getByBlog(String blogId);

    /**
     * 删除评论，根据id
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 根据评论id查询点赞数量
     *
     * @param commentId
     * @return
     */
    int getGoodsCount(String commentId);

    /**
     * 根据评论id和用户id点赞
     *
     * @param commentGoods
     */

    void goodByCommentIdAndUser(CommentGoods commentGoods);

    /**
     * 分页查询
     *
     * @param page
     */
    Page<Comment> getByPage(Page<Comment> page);

    /**
     * 后台分页查询
     *
     * @param page
     * @return
     */
    Page<Comment> getByPageBack(Page<Comment> page);


//    /**
//     * 修改
//     * @param comment
//     */
//    void update(Comment comment);
}
