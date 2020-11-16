package com.jg.blog.dao;

import com.jg.blog.pojo.BlogGoods;
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
public interface BlogGoodsDao extends MongoRepository<BlogGoods, String> {
    /**
     * 根据用户id和博客id查询数量
     *
     * @param userId
     * @param blogId
     * @return
     */
    int countByUserIdAndBlogId(Integer userId, String blogId);

}
