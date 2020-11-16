package com.jg.blog.mapper;


import com.jg.blog.pojo.User;
import com.jg.blog.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 用户表Mapper
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Component
public interface UserMapper {
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
     * 删
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
    List<User> getByPage(Page<User> page);

    /**
     * 查询总数
     *
     * @param page
     * @return
     */
    int getCountByPage(Page<User> page);

    /**
     * 增加
     *
     * @param user
     */
    void save(User user);

    /**
     * 重置密码
     *
     * @param userIds
     */
    List<User> getByIds(List<Integer> userIds);

    /**
     * 根据用户名查询用户
     *
     * @return
     */
    User getByUsername(String username);

    /**
     * 修改个人信息
     *
     * @param user
     */
    void updateInfo(User user);
}
