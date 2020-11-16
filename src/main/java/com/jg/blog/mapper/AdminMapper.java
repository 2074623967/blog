package com.jg.blog.mapper;

import com.jg.blog.pojo.Admin;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 管理员表Mapper
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Component
public interface AdminMapper {
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    Admin getByUsername(String username);

    /**
     * 查询管理员
     */
    Admin getAdmin();

    /**
     * 更新管理员信息
     *
     * @param admin
     */
    void update(Admin admin);
}
