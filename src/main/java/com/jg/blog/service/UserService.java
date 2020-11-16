package com.jg.blog.service;


import com.jg.blog.pojo.User;
import com.jg.blog.utils.Page;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表服务层接口
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
public interface UserService {

    /**
     * 更新
     *
     * @param user
     */
    void update(User user);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 删除
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    Page<User> getByPage(Page<User> page);

    /**
     * 保存
     *
     * @param user
     */
    void save(User user);

    /**
     * 重置密码为123456
     */
    void resetPwd(List<Integer> userIds);

    /**
     * 注册
     *
     * @param user
     */
    void register(User user);

    /**
     * 用户名查询
     * 根据
     *
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 修改个人信息
     *
     * @param user
     */
    void updateInfo(User user);

    /**
     * 查询收藏和评论数量
     *
     * @return
     */
    Map<String, Object> getCommentAndCollectionCount();
}
