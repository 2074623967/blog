package com.jg.blog.service.impl;

import com.jg.blog.enums.StateEnums;
import com.jg.blog.mapper.MusicMapper;
import com.jg.blog.pojo.About;
import com.jg.blog.pojo.Music;
import com.jg.blog.service.MusicService;
import com.jg.blog.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * author 老唐
 * time 2020-5-17
 * age:21
 *
 * @author adminstrator
 */
@Service
public class MusicServiceImpl implements MusicService {
    @Autowired
    private MusicMapper musicMapper;

    @Override
    public void save(Music music) {
        musicMapper.save(music);
    }

    @Override
    public void update(Music music) {
        musicMapper.update(music);
    }

    @Override
    public Music getById(Integer id) {
        return musicMapper.getById(id);
    }

    @Override
    public void deleteById(Integer id) {
        musicMapper.deleteById(id);
    }

    @Override
    public void enabledById(Integer id) {
        Music music = musicMapper.getById(id);
        music.setEnabled(StateEnums.ENABLED.getCode());
        musicMapper.update(music);
    }

    @Override
    public void disabledById(Integer id) {
        Music music = musicMapper.getById(id);
        music.setEnabled(StateEnums.NOT_ENABLE.getCode());
        musicMapper.update(music);
    }

    @Override
    public Page<Music> getByPage(Page<Music> page) {
        //查询数据，再查总数
        List<Music> list = musicMapper.getByPage(page);
        page.setList(list);
        int totalCount = musicMapper.getCountByPage(page);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<Music> getList() {
        return musicMapper.getList();
    }
}
