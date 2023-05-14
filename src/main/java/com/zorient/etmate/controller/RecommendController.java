package com.zorient.etmate.controller;

import com.zorient.etmate.pojo.Book;
import com.zorient.etmate.pojo.Film;
import com.zorient.etmate.pojo.Game;
import com.zorient.etmate.pojo.Result;
import com.zorient.etmate.service.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class RecommendController {
    @Autowired
    private RecommendService recommendService;

    /*
    * 基于用户的协同过滤算法，个性化推荐电影
    * */
    @GetMapping("/recommend/film/{id}")
    public Result userCfRecommendFilm(@PathVariable Integer id){
        List<Film> recommendFilmList=recommendService.userCfRecommendFilm(id);

        return Result.success(recommendFilmList);
    }

    /*
    * 基于用户的协同过滤算法，个性化推荐游戏
    * */
    @GetMapping("/recommend/game/{id}")
    public Result userCfRecommendGame(@PathVariable Integer id){
        List<Game> recommendGameList=recommendService.userCfRecommendGame(id);

        return Result.success(recommendGameList);
    }

    /**/
    @GetMapping("/recommend/book/{id}")
    public Result userCfRecommendBook(@PathVariable Integer id){
        List<Book> recommendBookList=recommendService.userCfRecommendBook(id);

        return Result.success(recommendBookList);
    }


}
