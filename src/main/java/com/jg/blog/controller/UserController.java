package com.jg.blog.controller;

import com.jg.blog.enums.ResultEnum;
import com.jg.blog.enums.StateEnums;
import com.jg.blog.pojo.User;
import com.jg.blog.pojo.Result;
import com.jg.blog.service.UserService;
import com.jg.blog.token.UsernamePasswordToken;
import com.jg.blog.utils.Page;
import com.jg.blog.utils.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author 老唐
 * time 2020-5-17
 * age:21
 *
 * @author adminstrator
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 增加
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result<Object> save(@RequestBody User user) {
        userService.save(user);
        return new Result<>("添加成功");

    }

    /**
     * 修改
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result<Object> update(@RequestBody User user) {
        userService.update(user);
        return new Result<>("更新成功");
    }

    /**
     * 修改个人信息
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateInfo", method = RequestMethod.PUT)
    public Result<Object> updateInfo(@RequestBody User user) {
        userService.updateInfo(user);
        return new Result<>("更新成功");
    }

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.POST)
    public Result<User> getById(@PathVariable Integer id) {
        User user = userService.getById(id);
        return new Result<>(user);
    }

    /**
     * 根据id删除
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public Result<Object> delete(@PathVariable Integer id) {
        userService.deleteById(id);
        return new Result<>("删除成功");
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "/getByPage", method = RequestMethod.POST)
    public Result<Page<User>> getByPage(@RequestBody Page<User> page) {
        //得到排序方式
        String sortColumn = page.getSortColumn();
        if (StringUtils.isNoneBlank(sortColumn)) {
            //如果排序不为空
            String[] sortColumns = {"sex", "created_time", "update_time"};
            List<String> sortList = Arrays.asList(sortColumns);
            if (!sortList.contains(sortColumn.toLowerCase())) {
                return new Result<>(ResultEnum.PARAMS_ERROR.getCode(), "排序参数不合法！");
            }
        }
        page = userService.getByPage(page);
        return new Result<>(page);
    }

    /**
     * 批量
     * 重置密码
     *
     * @return
     */
    @RequestMapping(value = "/resetPwd", method = RequestMethod.PUT)
    public Result<Object> resolvePwd(@RequestBody List<Integer> userIds) {
        userService.resetPwd(userIds);
        return new Result<>("重置完毕");
    }

    /**
     * 注册
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result<Object> register(@RequestBody User user) {
        userService.register(user);
        return new Result<>("注册成功");
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result<Object> login(@RequestBody User user) {
        if (user == null || StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword())) {
            return new Result(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误!");
        }
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UsernamePasswordToken(user.getUsername(), user.getPassword(), StateEnums.USER.getCode());
        try {
            subject.login(authenticationToken);
        } catch (Exception e) {
            return new Result(ResultEnum.PARAMS_NULL.getCode(), "用户名或密码错误!");
        }
        /***
         * 登录成功
         */
        Serializable sessionId = subject.getSession().getId();
        User u = (User) subject.getPrincipal();
        u.setPassword("");
        user.setDeleted(null);
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("token", sessionId);
        resultMap.put("user", u);
        return new Result(resultMap);
    }

    /**
     * 查询当前用户的评论数和收藏数
     *
     * @return
     */
    @RequestMapping(value = "/commentAndCollectionCount", method = RequestMethod.GET)
    public Result<Map<String, Object>> commentAndCollectionCount() {
        Map<String, Object> countMap = userService.getCommentAndCollectionCount();
        return new Result<>(countMap);
    }
}
