package com.jg.blog.service.impl;

import com.jg.blog.excel.entity.ExportParams;
import com.jg.blog.excel.handler.ExcelExportHandler;
import com.jg.blog.mapper.LogMapper;
import com.jg.blog.pojo.About;
import com.jg.blog.pojo.Log;
import com.jg.blog.service.LogService;
import com.jg.blog.utils.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 接口访问日志表服务实现类
 * </p>
 *
 * @author 稽哥
 * @date 2020-02-07 14:04:12
 * @Version 1.0
 */
@Service
@Slf4j
public class LogServiceImpl implements LogService {
    /**
     * 引入
     */
    @Autowired
    private LogMapper logmapper;


    @Override
    public void save(Log logger) {
        this.logmapper.save(logger);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<Log> getByPage(Page<Log> page) {
        //查询数据，再查总数
        List<Log> list = logmapper.getByPage(page);
        page.setList(list);
        int totalCount = logmapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public void deleteById(Integer id) {
        logmapper.deleteById(id);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        logmapper.deleteByIds(ids);

    }

    @Override
    public Workbook export() {
        List<Log> logList = logmapper.getAll();
        return new ExcelExportHandler().createSheet(new ExportParams("最新日志", "sheet1"), Log.class, logList);
    }
}
