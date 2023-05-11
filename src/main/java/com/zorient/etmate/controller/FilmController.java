package com.zorient.etmate.controller;

/*
 * 电影管理controller
 * */

import com.zorient.etmate.anno.Log;
import com.zorient.etmate.pojo.Film;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.pojo.Result;
import com.zorient.etmate.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
public class FilmController {

    @Autowired
    private FilmService filmService;

    /*
    * 条件分页查询电影信息
    * */
    @GetMapping("/film")
    public Result selectByCondition(@RequestParam(required = false) String filmName,
                                    @RequestParam(required = false) String genres,
                                    @RequestParam(required = false) String languages,
                                    @RequestParam(required = false) String regions,
                                    @RequestParam(required = false) Integer releaseYear,
                                    @RequestParam(required = false) String tags,
                                    @RequestParam(defaultValue = "1") Integer sortId,
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "35") Integer pageSize) {

        log.info("filmName:{},genres:{},languages:{},regions:{},releaseYear:{},tags:{}",
                filmName,genres,languages,regions,releaseYear,tags);

        PageBean pageBean = filmService.selectByCondition(filmName,genres,languages,regions,releaseYear,tags,sortId,page,pageSize);

        return Result.success(pageBean);
    }


    /*
    * 根据id查询电影信息
    * */
    @GetMapping("/film/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("查询的电影id：{}",id);
        Film film=filmService.selectById(id);

        return Result.success(film);
    }

    /*
    * 根据id删除（批量删除）电影
    * */
    @Log
    @DeleteMapping("/film/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids){
        filmService.deleteByIds(ids);

        return Result.success();
    }

    /*
    * 添加电影
    * */
    @Log
    @PostMapping("/film")
    public Result insertFilm(@RequestBody Film film){
        log.info("添加的电影信息：{}",film);
        filmService.insertFilm(film);

        return Result.success();
    }

    /*
    * 修改电影信息
    * */
    @Log
    @PutMapping("/film")
    public Result updateFilm(@RequestBody Film film){
        log.info("更新的电影信息：{}",film);
        filmService.updateFilm(film);

        return Result.success();
    }

    /*
    * 根据电影详情推荐相关电影
    * */
    @GetMapping("/film/similarFilm/{id}")
    public Result getSimilarFilms(@PathVariable Integer id){
        log.info("需要推荐的电影id：{}",id);
        PageBean pageBean=filmService.getSimilarFilms(id,1,5);

        return Result.success(pageBean);
    }
}















