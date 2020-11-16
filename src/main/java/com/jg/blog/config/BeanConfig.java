package com.jg.blog.config;

import com.jg.blog.utils.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author adminstrator
 */
@Configuration
public class BeanConfig {
    /**
     * 雪花算法
     */
    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
