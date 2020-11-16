package com.jg.blog.service.impl;

import com.jg.blog.enums.ResultEnum;
import com.jg.blog.enums.StateEnums;
import com.jg.blog.exception.BlogException;
import com.jg.blog.mapper.AboutMapper;
import com.jg.blog.pojo.About;
import com.jg.blog.pojo.Type;
import com.jg.blog.service.AboutService;
import com.jg.blog.utils.Page;
import com.jg.blog.vo.BlogVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * author 老唐
 * time 2020-5-17
 * age:21
 *
 * @author adminstrator
 */
@Service
@Slf4j
public class AboutServiceImpl implements AboutService {
    @Autowired
    private AboutMapper aboutMapper;

    @Override
    public void save(About about) {
        aboutMapper.save(about);
    }

    @Override
    public void update(About about) {
        aboutMapper.updateById(about);

    }

    @Override
    public About getById(Integer id) {
        return aboutMapper.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        aboutMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public About read() {
        About about = aboutMapper.getAbout();
        if (about == null) {
            return null;
        }
        //更新阅读数
        about.setAboutRead(about.getAboutRead() + 1);
        aboutMapper.updateById(about);
        return about;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enabledById(Integer id) {
        //查询是否已经存在启用的关于我的
        About about = aboutMapper.getAbout();
        if (about != null) {
            throw new BlogException(ResultEnum.ERROR.getCode(), "当前已存在启用中的关于我的");
        }
        about = aboutMapper.getById(id);
        System.out.println(about);
        about.setEnable(StateEnums.ENABLED.getCode());
        aboutMapper.updateEnable(about);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disabledById(Integer id) {
        //查询是否已经存在启用的关于我的
        About about = aboutMapper.getById(id);
        about.setEnable(StateEnums.NOT_ENABLE.getCode());
        aboutMapper.updateEnable(about);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Page<About> getByPage(Page<About> page) {
        //查询数据，再查总数
        List<About> list = aboutMapper.getByPage(page);
        page.setList(list);
        int totalCount = aboutMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }
}
