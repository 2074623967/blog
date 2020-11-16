package com.jg.blog.pojo;

import org.springframework.data.annotation.Id;

/**
 * 点赞实体
 * author 老唐
 * time 2020-5-17
 * age:21
 *
 * @author adminstrator
 */
public class Goods {
    /**
     * 点赞id
     */
    @Id
    private String id;
    /**
     * 用户id
     */
    private Integer uerId;
    /**
     * 博客id
     */
    private String blogId;
}
