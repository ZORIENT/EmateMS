package com.zorient.etmate.controller;

import com.zorient.etmate.anno.Log;
import com.zorient.etmate.pojo.Game;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.pojo.Result;
import com.zorient.etmate.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class GameController {
    @Autowired
    private GameService gameService;

    /*
    * 分页查询游戏信息
    * */
    @GetMapping("/game")
    public Result selectByCondition(@RequestParam(required = false) String gameName,
                                    @RequestParam(required = false) String platforms,
                                    @RequestParam(required = false) Integer releaseYear,
                                    @RequestParam(required = false) String genres,
                                    @RequestParam(required = false) String publisher,
                                    @RequestParam(required = false) String tags,
                                    @RequestParam(defaultValue = "1") Integer sortId,
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "35") Integer pageSize){
        log.info("gameName:{},platform:{},releaseYear:{},genres:{},publisher:{},tags:{},sortId:{},page:{},pageSize:{}",
                gameName,platforms,releaseYear,genres,publisher,tags,sortId,page,pageSize);

        PageBean pageBean=gameService.selectByCondition(gameName,platforms,releaseYear,genres,publisher,tags,sortId,page,pageSize);

        return Result.success(pageBean);
    }

    /*
    * 根据id查询游戏信息
    * */
    @GetMapping("/game/{id}")
    public Result selectById(@PathVariable Integer id){
        log.info("查询的游戏id：{}",id);
        Game game=gameService.selectById(id);

        return Result.success(game);
    }

    /*
    * 批量删除游戏信息
    * */
    @Log
    @DeleteMapping("/game/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids){
        gameService.deleteByIds(ids);

        return Result.success();
    }

    /*
    * 添加游戏信息
    * */
    @Log
    @PostMapping("/game")
    public Result insertGame(@RequestBody Game game){
        log.info("添加的游戏信息：{}",game);
        gameService.insertGame(game);

        return Result.success();
    }

    /*
    * 修改游戏信息
    * */
    @Log
    @PutMapping("/game")
    public Result updateGame(@RequestBody Game game){
        log.info("修改的游戏信息为：{}",game);
        gameService.updateGame(game);

        return Result.success();
    }

    /*
     * 根据游戏详情推荐相关游戏
     * */
    @GetMapping("/game/similarGame/{id}")
    public Result getSimilarGames(@PathVariable Integer id){
        log.info("需要推荐的游戏id：{}",id);
        PageBean pageBean=gameService.getSimilarGames(id,1,5);

        return Result.success(pageBean);
    }

}
