package com.zorient.etmate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zorient.etmate.mapper.GameMapper;
import com.zorient.etmate.pojo.Game;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameMapper gameMapper;

    /*
    * 条件分页查询游戏信息
    * */
    @Override
    public PageBean selectByCondition(String gameName, String platforms, Integer releaseYear, String genres,
                                      String publisher, String tags, Integer sortId, Integer page, Integer pageSize) {
        String  sort=new String("douban_score");
        switch(sortId){
            case 1:
                sort="game_name";
                break;
            case 2:
                sort="time_platform";
                break;
            default:
                sort="platforms";
                break;
        }

        log.info("sort的值：{}",sort);

        //使用pagehelper设置分页参数
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<Game> gameList=gameMapper.selectByCondition(gameName,platforms,releaseYear,genres,publisher,tags,sort);
        Page<Game> p= (Page<Game>) gameList;

        //结果封装PageBean
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        log.info("条件查询游戏：{}",pageBean);

        return pageBean;
    }

    /*
    * 根据id查询游戏信息
    * */
    @Override
    public Game selectById(Integer id) {
        Game game=gameMapper.selectById(id);

        return game;
    }

    /*
    * 批量删除游戏信息
    * */
    @Override
    public void deleteByIds(List<Integer> ids) {
        gameMapper.deleteByIds(ids);
    }

    /*
    * 添加游戏信息
    * */
    @Override
    public void insertGame(Game game) {
        gameMapper.insertGame(game);
    }

    /*
    * 修改游戏信息
    * */
    @Override
    public void updateGame(Game game) {
        gameMapper.updateGame(game);
    }
}
