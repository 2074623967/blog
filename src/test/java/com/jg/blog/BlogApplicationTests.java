package com.jg.blog;

import com.jg.blog.dao.CommentDao;
import com.jg.blog.pojo.Blog;
import com.jg.blog.pojo.Comment;
import com.jg.blog.pojo.User;
import com.jg.blog.utils.IdWorker;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jws.soap.SOAPBinding;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private IdWorker idWorker;

    @Test
    public void test() {
        Comment comment = new Comment();
        comment.setId(idWorker.nextId() + "");
        comment.setCommentBlog("123");
        comment.setCommentContent("我是评论2");
        comment.setCommentUser(369);
        comment.setCommentGood(10);
        comment.setCreatedTime(new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));
        //插入帖子
        Blog blog = new Blog();
        blog.setBlogId("123123");
        blog.setBlogTitle("我是帖子");
        comment.setBlog(blog);
        //插入用户
        User user = new User();
        user.setUserId(123);
        user.setName("老唐");
        comment.setUser(user);
        commentDao.insert(comment);
    }

    @Test
    public void toFindAll() {
        List<Comment> list = commentDao.findAll();
        for (Comment comment : list) {
            System.out.println(comment);

        }
    }

    @Test
    public void testJpa() {
        List<Comment> commentList = commentDao.findByCommentUserEqualsAndCommentGoodGreaterThanEqual(369, 6);
        for (Comment comment1 : commentList) {
            System.out.println(comment1);
        }
    }

    @Test
    public void testPage() {
        Comment comment = new Comment();
        comment.setCommentBlog("1264365631460507648");
        Example<Comment> example = Example.of(comment);
        Pageable pageable = PageRequest.of(0, 5);
        Page<Comment> page = commentDao.findAll(example, pageable);
        long totalElement = page.getTotalElements();
        int totalPage = page.getTotalPages();
        List<Comment> content = page.getContent();
        System.out.println(totalElement);
        System.out.println(totalPage);
        for (Comment comment1 : content) {
            comment1.setBlog(null);
            System.out.println(comment1);
        }
    }

    @Test
    public void testPage2() {
        Pageable pageable = PageRequest.of(0, 5);
        Page<Comment> page = commentDao.findAllByCommentContentIsLike("6", pageable);
        long totalElement = page.getTotalElements();
        int totalPage = page.getTotalPages();
        List<Comment> content = page.getContent();
        System.out.println(totalElement);
        System.out.println(totalPage);
        for (Comment comment1 : content) {
            comment1.setBlog(null);
            System.out.println(comment1);
        }
    }
}
