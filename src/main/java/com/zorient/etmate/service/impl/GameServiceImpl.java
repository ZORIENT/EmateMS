package com.zorient.etmate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zorient.etmate.exception.AppException;
import com.zorient.etmate.exception.AppExceptionCodeMsg;
import com.zorient.etmate.mapper.CollectionMapper;
import com.zorient.etmate.mapper.CommentMapper;
import com.zorient.etmate.mapper.GameMapper;
import com.zorient.etmate.pojo.Game;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CollectionMapper collectionMapper;

    /*
    * 条件分页查询游戏信息
    * */
    @Override
    public PageBean selectByCondition(String gameName, String platforms, Integer releaseYear, String genres,
                                      String publisher, String tags, Integer sortId, Integer page, Integer pageSize) {
        String  sort = switch (sortId) {
            case 1 -> "game_name";
            case 2 -> "time_platform";
            case 3 -> "platforms";
            default -> throw new AppException(AppExceptionCodeMsg.PARAM_ERROR);
        };

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

        if(game!=null){
            // 查询结果不为空
            return game;
        }else{
            throw new AppException(AppExceptionCodeMsg.RESOURCE_NOT_AVAILABLE);
        }
    }

    /*
    * 批量删除游戏信息
    * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByIds(List<Integer> ids) {
        // 删除相关评论和收藏
        for(Integer id:ids){
            commentMapper.deleteCommentsByItemId(id,(short)2);
            collectionMapper.deleteCollectionsByCollectionId(id,(short)2);
        }

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

    /*
    * 根据游戏id推荐相关游戏
    * */
    @Override
    public PageBean getSimilarGames(Integer id, int page, int pageSize) {
        Game game=gameMapper.selectById(id);

        String[] tag=game.getTags().split("/");
        String[] publisher=game.getPublisher().split("/");

        //使用pagehelper设置分页参数
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<Game> gameList=gameMapper.getSimilarGames(id,tag[0],publisher[0],game.getGenres());
        Page<Game> p= (Page<Game>) gameList;

        //结果封装PageBean
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        log.info("条件查询游戏：{}",pageBean);

        return pageBean;
    }
}
