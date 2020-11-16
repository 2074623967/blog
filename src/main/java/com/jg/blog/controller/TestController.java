package com.jg.blog.controller;

import com.jg.blog.exception.BlogException;
import com.jg.blog.pojo.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author adminstrator
 */
@RestController
@RequestMapping("/test")
public class TestController {
    /**
     * 测试异常
     */
    @RequestMapping(value = "/testException/{id}", method = RequestMethod.GET)
    public Result<Object> testException(@PathVariable Integer id) {
        if (id == 1) {
            return new Result<>();
        } else {
            throw new BlogException("发生了异常!");
        }
    }
}
