package com.jg.blog.dao;

import com.jg.blog.pojo.BlogCollection;
import com.jg.blog.pojo.BlogGoods;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * author 老唐
 * time 2020-5-17
 * age:21
 *
 * @author adminstrator
 */
@Repository
public interface CollectionDao extends MongoRepository<BlogCollection, String> {
    /**
     * 根据用户id和博客Id查询
     *
     * @param blogId
     * @param userId
     * @return
     */
    int countByBlogIdAndUserId(String blogId, Integer userId);

    /**
     * 得到收藏数量
     *
     * @param userId
     * @return
     */
    int countByUserId(Integer userId);
}
