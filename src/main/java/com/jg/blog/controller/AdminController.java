package com.jg.blog.controller;

import com.jg.blog.enums.ResultEnum;
import com.jg.blog.enums.StateEnums;
import com.jg.blog.pojo.Admin;
import com.jg.blog.pojo.Result;
import com.jg.blog.service.AdminService;
import com.jg.blog.token.UsernamePasswordToken;
import com.jg.blog.utils.ShiroUtils;
import com.jg.blog.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * author 老唐
 * time 2020-5-17
 * age:21参数校验
 *
 * @author adminstrator
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<Object> login(@RequestBody Admin admin) {
        if (admin == null || StringUtils.isBlank(admin.getUsername()) || StringUtils.isBlank(admin.getPassword())) {
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误！");
        }
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UsernamePasswordToken(admin.getUsername(), admin.getPassword(), StateEnums.ADMIN.getCode());
        try {
            subject.login(authenticationToken);
        } catch (Exception e) {
            return new Result(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误!");
        }
        /***
         * 登录成功
         */
        Serializable sessionId = subject.getSession().getId();
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("token", sessionId);
        return new Result(resultMap);
    }

    /**
     * 获取当前登录用户信息
     *
     * @return
     */
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Result<Admin> getLoginInfo() {
        Admin loginAdmin = (Admin) ShiroUtils.getLoginUser();
        loginAdmin.setPassword("");
        return new Result<>(loginAdmin);
    }

    @RequestMapping(value = "getAdmin", method = RequestMethod.GET)
    public Result<Admin> getAdmin() {
        Admin admin = adminService.getAdmin();
        return new Result<>(admin);
    }

    /**
     * 更新个人信息
     *
     * @param admin
     * @return
     */
    @RequestMapping(value = "/updateInfo", method = RequestMethod.PUT)
    public Result<Object> updateInfo(@RequestBody Admin admin) {
        adminService.update(admin);
        return new Result<>("更新成功");
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    public Result<Object> updatePassword(@RequestBody Admin admin) {
        adminService.updatePassword(admin);
        return new Result<>("更新成功");
    }
}
