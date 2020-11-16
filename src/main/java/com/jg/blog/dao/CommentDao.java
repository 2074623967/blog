package com.jg.blog.dao;

import com.jg.blog.pojo.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
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
public interface CommentDao extends MongoRepository<Comment, String> {
    /**
     * 根据评论人id和点赞量查询>=
     *
     * @return
     */
    List<Comment> findByCommentUserEqualsAndCommentGoodGreaterThanEqual(Integer userId, Integer goodsCount);

    /**
     * 根据博客id查询
     * 根据创建时间和点赞数排序
     *
     * @param blogId
     * @return
     */
    List<Comment> findByCommentBlogOrderByCreatedTimeDescCommentGoodDesc(String blogId);

    /**
     * 根据评论内容模糊查询
     *
     * @param content
     * @param pageable
     * @return
     */
    Page<Comment> findAllByCommentContentIsLike(String content, Pageable pageable);

    /**
     * 得到评论数量
     *
     * @param userId
     * @return
     */
    int countByCommentUser(Integer userId);

    /**
     * 根据博客id查询
     *
     * @param blogId
     * @return
     */
    @Query(value = "{'commentBlog': ?0}", fields = "{'id': 1, 'commentGoods':1}")
    List<Comment> getByBlog(String blogId);

    /**
     * 查询点赞数大于多少的博客
     *
     * @param num
     * @return
     */
    @Query(value = "{'commentGood': {$gt: ?0}}", fields = "{'id': 1, 'commentGoods':1}")
    List<Comment> getByCommentGoods(Integer num);

    /**
     * 根据博客标题模糊查询
     *
     * @param comment
     * @return
     */
    @Query(value = "{'blog.blogTitle': {$regex: ?#{[0].blog.blogTitle}}}", fields = "{'id': 1, 'blog.blogTitle': 1}")
    List<Comment> getByBlog(Comment comment);

    /**
     * 根据博客标题和用户昵称模糊查询
     *
     * @param comment
     * @param pageable
     * @return
     */
    @Query("{" +
            "'blog.blogTitle': {$regex: ?#{[0].blog.blogTitle}}," +
            "'user.nickname' : {$regex: ?#{[0].user.nickname}}" +
            "}")
    Page<Comment> getByBlogTitleAndNickname(Comment comment, Pageable pageable);

}
