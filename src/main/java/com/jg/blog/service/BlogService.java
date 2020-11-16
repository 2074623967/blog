package com.jg.blog.service;

import com.jg.blog.pojo.Blog;
import com.jg.blog.pojo.BlogCollection;
import com.jg.blog.pojo.BlogGoods;
import com.jg.blog.pojo.CommentGoods;
import com.jg.blog.utils.Page;
import com.jg.blog.vo.BlogVo;

import java.util.List;

/**
 * <p>
 * 博客表服务层接口
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
public interface BlogService {

    /**
     * 增加
     *
     * @param blog
     */
    void save(Blog blog);

    /**
     * 修改
     *
     * @param blog
     */
    void update(Blog blog);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Blog getById(String id);

    /**
     * 阅读
     *
     * @param id
     * @return
     */
    BlogVo read(String id);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(String id);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    Page<BlogVo> getByPage(Page<BlogVo> page);

    /**
     * 推荐阅读
     *
     * @return
     */
    List<BlogVo> recomRead();

    /**
     * 时间轴
     *
     * @return
     */
    List<BlogVo> getTimeLine();

    /**
     * 根据博客id和用户id点赞
     *
     * @param blogGoods
     */
    void goodByBlogAndUser(BlogGoods blogGoods);

    /**
     * 根据博客id查询点赞数
     *
     * @param blogId
     * @return
     */
    int getGoodsCount(String blogId);

    /**
     * 收藏
     *
     * @param blogCollection
     */
    void collectionByBlogId(BlogCollection blogCollection);

    /**
     * 根据博客id查询收藏数
     *
     * @param blogId
     * @return
     */
    int getCollectionCount(String blogId);

    /**
     * 分页查询我的收藏
     *
     * @param page
     * @return
     */
    Page<BlogCollection> getCollectionByPage(Page<BlogCollection> page);
}
