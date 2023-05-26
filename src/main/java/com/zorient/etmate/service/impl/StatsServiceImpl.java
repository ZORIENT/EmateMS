package com.zorient.etmate.service.impl;

import com.zorient.etmate.mapper.StatsMapper;
import com.zorient.etmate.pojo.Category;
import com.zorient.etmate.pojo.Stats;
import com.zorient.etmate.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private StatsMapper statsMapper;

    @Override
    public Stats getStats() {
        // 获取电影数量
        Integer filmCount=statsMapper.getFilmCount();
        // 获取游戏数量
        Integer gameCount=statsMapper.getGameCount();
        // 获取书籍数量
        Integer bookCount=statsMapper.getBookCount();
        //获取用户数量
        Integer userCount=statsMapper.getUserCount();

        return new Stats(filmCount,gameCount,bookCount,userCount);
    }

    @Override
    public List<Category> getFilmStats() {
        // 获取电影分类信息

        return statsMapper.getFilmStats();
    }

    @Override
    public List<Category> getGameStats() {
        //获取游戏分类信息

        return statsMapper.getGameStats();
    }

    @Override
    public List<Category> getBookStats() {
        //获取书籍分类信息

        return statsMapper.getBookStats();
    }
}
