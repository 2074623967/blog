package com.jg.blog.service.impl;

import com.jg.blog.dao.BlogGoodsDao;
import com.jg.blog.dao.CollectionDao;
import com.jg.blog.mapper.BlogMapper;
import com.jg.blog.mapper.TypeMapper;
import com.jg.blog.pojo.*;
import com.jg.blog.service.BlogService;
import com.jg.blog.utils.IdWorker;
import com.jg.blog.utils.Page;
import com.jg.blog.utils.ShiroUtils;
import com.jg.blog.vo.BlogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 博客表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Service
@Slf4j
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private BlogGoodsDao blogGoodsDao;
    @Autowired
    private CollectionDao collectionDao;
    @Autowired
//雪花算法
    private IdWorker idWorker;
    @Autowired
    private TypeMapper typeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Blog blog) {
        blog.setBlogId(idWorker.nextId() + "");
        blogMapper.save(blog);
        //取出分类，分类博客数量+1
        Integer blogType = blog.getBlogType();
        Type type = typeMapper.getById(blogType);
        type.setTypeBlogCount(type.getTypeBlogCount() + 1);
        typeMapper.update(type);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Blog blog) {
        Blog oldblog = blogMapper.getById(blog.getBlogId());
        blogMapper.update(blog);
        //判断分类1是否改变
        Integer oldTypeId = oldblog.getBlogType();
        Integer newTypeId = blog.getBlogType();
        if (!oldTypeId.equals(newTypeId)) {
            Type oldType = typeMapper.getById(oldTypeId);
            oldType.setTypeBlogCount(oldType.getTypeBlogCount() - 1);
            typeMapper.update(oldType);
            Type nowType = typeMapper.getById(newTypeId);
            nowType.setTypeBlogCount(nowType.getTypeBlogCount() + 1);
            typeMapper.update(nowType);
        }
    }

    @Override
    public Blog getById(String id) {
        return blogMapper.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BlogVo read(String id) {
        Blog blog = blogMapper.getById(id);
        //阅读，需要更新阅读树
        blog.setBlogRead(blog.getBlogRead() + 1);
        blogMapper.update(blog);
        //blog->blogVo
        BlogVo blogVo = new BlogVo();
        BeanUtils.copyProperties(blog, blogVo);
        Type type = typeMapper.getById(blog.getBlogType());
        blogVo.setTypeName(type.getTypeName());
        return blogVo;
    }

    @Override
    public void deleteById(String id) {
        blogMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<BlogVo> getByPage(Page<BlogVo> page) {
        //查询数据，再查总数
        List<BlogVo> list = blogMapper.getByPage(page);
        page.setList(list);
        int totalCount = blogMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<BlogVo> recomRead() {
        return blogMapper.recomRead();
    }

    @Override
    public List<BlogVo> getTimeLine() {
        return blogMapper.getTimeLine();
    }

    @Override
    public void goodByBlogAndUser(BlogGoods blogGoods) {
        User user = (User) ShiroUtils.getLoginUser();
        blogGoods.setUserId(user.getUserId());
        //去除博客id，点赞数+1
        String blogId = blogGoods.getBlogId();
        blogMapper.updateGoods(blogId);
        try {
            blogGoods.setId(idWorker.nextId() + "");
            blogGoodsDao.save(blogGoods);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getGoodsCount(String blogId) {
        User user = (User) ShiroUtils.getLoginUser();
        int count = blogGoodsDao.countByUserIdAndBlogId(user.getUserId(), blogId);
        return count;
    }

    @Override
    public void collectionByBlogId(BlogCollection blogCollection) {
        User user = (User) ShiroUtils.getLoginUser();
        blogCollection.setUserId(user.getUserId());
        blogCollection.setUser(user);
        //查询博客
        Blog blog = blogMapper.getById(blogCollection.getBlogId());
        blog.setBlogContent(null);
        blogCollection.setBlog(blog);
        blog.setBlogCollection(blog.getBlogCollection() + 1);
        blogMapper.update(blog);
        try {
            blogCollection.setCollectionId(idWorker.nextId() + "");
            collectionDao.save(blogCollection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCollectionCount(String blogId) {
        User user = (User) ShiroUtils.getLoginUser();
        int count = collectionDao.countByBlogIdAndUserId(blogId, user.getUserId());
        return count;
    }

    @Override
    public Page<BlogCollection> getCollectionByPage(Page<BlogCollection> page) {
        User user = (User) ShiroUtils.getLoginUser();
        BlogCollection blogCollection = new BlogCollection();
        blogCollection.setUserId(user.getUserId());
        Example<BlogCollection> example = Example.of(blogCollection);
        Pageable pageable = PageRequest.of(page.getCurrentPage() - 1, page.getPageSize());
        org.springframework.data.domain.Page<BlogCollection> p = collectionDao.findAll(example, pageable);
        //封装一下总页数总条数数据
        page.setTotalCount((int) p.getTotalElements());
        page.setTotalPage(p.getTotalPages());
        page.setList(p.getContent());
        return page;
    }
}
