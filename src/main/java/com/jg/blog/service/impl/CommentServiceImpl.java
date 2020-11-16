package com.jg.blog.service.impl;


import com.jg.blog.dao.CommentDao;
import com.jg.blog.dao.CommentGoodsDao;
import com.jg.blog.mapper.BlogMapper;
import com.jg.blog.mapper.UserMapper;
import com.jg.blog.pojo.Blog;
import com.jg.blog.pojo.Comment;
import com.jg.blog.pojo.CommentGoods;
import com.jg.blog.pojo.User;
import com.jg.blog.service.CommentService;
import com.jg.blog.utils.IdWorker;
import com.jg.blog.utils.Page;
import com.jg.blog.utils.ShiroUtils;
import com.jg.blog.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 评论表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;
    @Autowired
    private CommentGoodsDao commentGoodsDao;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public void save(Comment comment) {
        //评论数+1
        Blog blog = blogMapper.getById(comment.getCommentBlog());
        blog.setBlogComment(blog.getBlogComment() + 1);
        blogMapper.update(blog);
        comment.setBlog(blog);
        comment.setId(idWorker.nextId() + "");
        comment.setCommentGood(0);
        comment.setCreatedTime(new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));
        //先查询出user和blog再执行插入操作
        User user = userMapper.getById(comment.getCommentUser());
        comment.setUser(user);
        commentDao.save(comment);
    }

    @Override
    public List<Comment> getByBlog(String blogId) {
        //查询博客评论
        List<Comment> commentList = commentDao.findByCommentBlogOrderByCreatedTimeDescCommentGoodDesc(blogId);
        //查询点赞情况
        //取出评论id
        List<String> commmentIds = commentList.stream().map(Comment::getId).collect(Collectors.toList());
        List<CommentGoods> commentGoodsList = commentGoodsDao.findByCommentIdIn(commmentIds);
        //便利去封装评论情况
        commentList.forEach(e -> {
            commentGoodsList.forEach(good -> {
                if (e.getId().equals(good.getCommentId())) {
                    //匹配到了评论记录
                    e.setCommentFlag(true);
                }
            });
        });
        return commentList;
    }

    @Override
    public void deleteById(String id) {
        commentDao.deleteById(id);
    }

    @Override
    public int getGoodsCount(String commentId) {
        User user = (User) ShiroUtils.getLoginUser();
        int count = commentGoodsDao.countByUserIdAndCommentId(user.getUserId(), commentId);
        return count;
    }

    @Override
    public void goodByCommentIdAndUser(CommentGoods commentGoods) {
        User user = (User) ShiroUtils.getLoginUser();
        commentGoods.setUserId(user.getUserId());
        //去除评论id，点赞数+1
        String commentId = commentGoods.getCommentId();
        Comment comment = commentDao.findById(commentId).get();
        comment.setCommentGood(comment.getCommentGood() + 1);
        commentDao.save(comment);
        try {
            commentGoods.setId(idWorker.nextId() + "");
            commentGoodsDao.save(commentGoods);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Page<Comment> getByPage(Page<Comment> page) {
        User user = (User) ShiroUtils.getLoginUser();
        Comment comment = new Comment();
        comment.setCommentUser(user.getUserId());
        Example<Comment> example = Example.of(comment);
        Pageable pageable = PageRequest.of(page.getCurrentPage() - 1, page.getPageSize());
        org.springframework.data.domain.Page<Comment> p = commentDao.findAll(example, pageable);
        //封装一下总页数总条数数据
        page.setTotalCount((int) p.getTotalElements());
        page.setTotalPage(p.getTotalPages());
        page.setList(p.getContent());
        return page;
    }

    @Override
    public Page<Comment> getByPageBack(Page<Comment> page) {
        Comment comment = new Comment();
        String blogTitle = (String) page.getParams().get("blogTitle");
        if (StringUtils.isBlank(blogTitle)) {
            blogTitle = "";
        }
        String nickname = (String) page.getParams().get("nickname");
        if (StringUtils.isBlank(nickname)) {
            nickname = "";
        }
        Blog blog = new Blog();
        blog.setBlogTitle(blogTitle);
        comment.setBlog(blog);
        User user = new User();
        user.setNickname(nickname);
        comment.setUser(user);
        Pageable pageable = PageRequest.of(page.getCurrentPage() - 1, page.getPageSize());
        org.springframework.data.domain.Page<Comment> p = commentDao.getByBlogTitleAndNickname(comment, pageable);
        //封装一下总页数总条数数据
        page.setTotalCount((int) p.getTotalElements());
        page.setTotalPage(p.getTotalPages());
        page.setList(p.getContent());
        return page;
    }
//
//    @Override
//    public void update(Comment comment) {
//
//    }
}
