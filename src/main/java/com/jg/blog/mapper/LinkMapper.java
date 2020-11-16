package com.jg.blog.mapper;


import com.jg.blog.pojo.Link;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 评论表Mapper
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Component
public interface LinkMapper {
    /**
     * 增加
     *
     * @param link
     */
    void save(Link link);

    /**
     * 查询所有
     *
     * @return
     */
    List<Link> getAll();

    /**
     * 查询单个
     *
     * @param id
     * @return
     */
    Link getById(Integer id);

    /**
     * 删除
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 更新
     *
     * @param link
     */
    void update(Link link);
}
