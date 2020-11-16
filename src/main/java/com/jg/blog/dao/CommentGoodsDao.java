package com.jg.blog.dao;

import com.jg.blog.pojo.Comment;
import com.jg.blog.pojo.CommentGoods;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * author 老唐
 * time 2020-5-17
 * age:21
 *
 * @author adminstrator
 */
@Repository
public interface CommentGoodsDao extends MongoRepository<CommentGoods, String> {
    /**
     * 根据用户id和评论Id查询
     *
     * @param userId
     * @param commentId
     * @return
     */
    int countByUserIdAndCommentId(Integer userId, String commentId);

    /**
     * 根据评论id查询
     *
     * @param commentIdS
     * @return
     */
    List<CommentGoods> findByCommentIdIn(List<String> commentIdS);
}
