package com.jg.blog.mapper;

import com.jg.blog.pojo.Blog;
import com.jg.blog.utils.Page;
import com.jg.blog.vo.BlogVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 博客表Mapper
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Component
public interface BlogMapper {
    /**
     * 增加
     *
     * @param blog
     */
    void save(Blog blog);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    Blog getById(String id);

    /**
     * 更新
     *
     * @param blog
     */
    void update(Blog blog);

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
    List<BlogVo> getByPage(Page<BlogVo> page);

    /**
     * 查询总数
     *
     * @param page
     * @return recomread  你看有这个方法没   你有事在哪搞的代码     xml配置  跟不上
     */
    int getCountByPage(Page<BlogVo> page);

    /**
     * 推荐阅读
     *
     * @return
     */
    List<BlogVo> recomRead();

    /**
     * 查询时间轴
     *
     * @return
     */
    List<BlogVo> getTimeLine();

    /***
     * 点赞—+1
     * @param blogId
     */
    void updateGoods(String blogId);
}
