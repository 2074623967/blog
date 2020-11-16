package com.jg.blog.service;


import com.jg.blog.pojo.Admin;

/**
 * <p>
 * 管理员表服务层接口
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
public interface AdminService {


    Admin getByUsername(String username);

    /**
     * 获取管理员信息
     *
     * @return
     */
    Admin getAdmin();

    /**
     * 更新管理员信息
     *
     * @param admin
     */
    void update(Admin admin);

    void updatePassword(Admin admin);
}
