package com.jg.blog.mapper;

import com.jg.blog.pojo.About;
import com.jg.blog.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

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
public interface AboutMapper {
    /**
     * 增加
     *
     * @param about
     */
    void save(About about);

    /**
     * 更新
     *
     * @param about
     */
    void updateById(About about);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    About getById(Integer id);

    /**
     * 查询关于
     *
     * @return
     */
    About getAbout();

    /**
     * 删除
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 启用
     *
     * @param about
     */
    void updateEnable(About about);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    List<About> getByPage(Page<About> page);

    /**
     * 查询总数
     *
     * @param page
     * @return
     */
    int getCountByPage(Page<About> page);
}
