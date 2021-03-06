package com.jg.blog.mapper;

import com.jg.blog.pojo.Log;
import com.jg.blog.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 接口访问日志表Mapper
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Component
public interface LogMapper {
    /**
     * 保存
     *
     * @param logger
     */
    void save(Log logger);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    List<Log> getByPage(Page<Log> page);

    /**
     * 查询总数
     *
     * @param page
     * @return
     */
    int getCountByPage(Page<Log> page);

    /**
     * 根据id删除
     *
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据ids批量删除
     *
     * @param ids
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 返回所有
     *
     * @return
     */
    List<Log> getAll();
}
