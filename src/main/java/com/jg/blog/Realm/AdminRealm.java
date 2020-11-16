package com.jg.blog.Realm;

import com.jg.blog.enums.ResultEnum;
import com.jg.blog.enums.StateEnums;
import com.jg.blog.exception.BlogException;
import com.jg.blog.pojo.Admin;
import com.jg.blog.pojo.User;
import com.jg.blog.service.AdminService;
import com.jg.blog.service.UserService;
import com.jg.blog.token.UsernamePasswordToken;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 处理管理员1登录和授权逻辑
 * author 老唐
 * time 2020-5-17
 * age:21
 *
 * @author adminstrator
 */
public class AdminRealm extends AuthorizingRealm {
    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /***
     * 认证
     * @param token
     * @return
     * @throws AuthenticationException
     */

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //强转
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        int state = usernamePasswordToken.getState();
        if (state == StateEnums.ADMIN.getCode()) {
            Admin admin = adminService.getByUsername(username);
            if (admin == null) {
                //用户不存在
                throw new BlogException(ResultEnum.ERROR.getCode(), "用户不存在");
            }
            return new SimpleAuthenticationInfo(admin, admin.getPassword(), this.getName());
        } else {
            User user = userService.getByUsername(username);
            if (user == null || user.getDeleted() == 1) {
                //用户不存在
                throw new BlogException(ResultEnum.ERROR.getCode(), "用户不存在");
            }
            return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
        }

    }
}
