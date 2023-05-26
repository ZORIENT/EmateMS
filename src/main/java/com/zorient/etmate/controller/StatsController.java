package com.zorient.etmate.controller;

import com.zorient.etmate.pojo.Category;
import com.zorient.etmate.pojo.Result;
import com.zorient.etmate.pojo.Stats;
import com.zorient.etmate.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StatsController {
    @Autowired
    private StatsService statsService;

    /*
    * 查询统计数据
    * */
    @GetMapping("/stats")
    public Result getStats(){
        Stats stats=statsService.getStats();

        return Result.success(stats);
    }

    /*
    * 获取电影分类统计数据
    * */
    @GetMapping("/stats/film")
    public Result getFilmStats(){
        List<Category> filmList=statsService.getFilmStats();

        return Result.success(filmList);
    }

    /*
    * 获取游戏分类统计数据
    * */
    @GetMapping("/stats/game")
    public Result getGameStats(){
        List<Category> gameList=statsService.getGameStats();

        return Result.success(gameList);
    }

    /*
    * 获取书籍分类统计数据
    * */
    @GetMapping("/stats/book")
    public Result getBookStats(){
        List<Category> bookList=statsService.getBookStats();

        return Result.success(bookList);
    }

}
