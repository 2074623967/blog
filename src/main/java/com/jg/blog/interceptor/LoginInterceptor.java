package com.jg.blog.interceptor;

import com.jg.blog.enums.ResultEnum;
import com.jg.blog.exception.BlogException;
import com.jg.blog.pojo.Admin;
import com.jg.blog.utils.ShiroUtils;
import com.jg.blog.utils.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author 老唐
 * time 2020-5-17
 * age:21
 *
 * @author adminstrator
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 放行的白名单
     */
    private static String[] whiteList = {
            "/admin/login",
            "/user/login",
            "/user/register",
            "/link/list",
            "/music/getByPage",
            "/about/read",
            "/blog/read",
            "/admin/getAdmin",
            "/music/getList",
            "/blog/recomRead",
            "/type/getList",
            "/blog/getTimeLine",
            "/blog/getByPage",
            "/comment/getByBlog"
    };

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (containWhiteList(request.getRequestURI())) {
            return true;
        }
        //获取token
        String token = request.getHeader("Authortication");
        if (StringUtils.isNotBlank(token)) {
            //token不为空，获取当前登录用户,强转一下
            Object loginUser = ShiroUtils.getLoginUser();
            if (loginUser != null) {
                //说明token有效
                return true;
            }
        }
        throw new BlogException(ResultEnum.NOT_LOGIN);
    }

    /**
     * 判断url是否在白名单里面
     *
     * @param s
     * @return
     */
    private boolean containWhiteList(String s) {
        for (String url : whiteList) {
            if (s.contains(url)) {
                return true;
            }
        }
        return false;
    }
}
