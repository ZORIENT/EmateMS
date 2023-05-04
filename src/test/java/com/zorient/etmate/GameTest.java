package com.zorient.etmate;

import com.zorient.etmate.mapper.GameMapper;
import com.zorient.etmate.pojo.Game;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class GameTest {
    @Autowired
    private GameMapper gameMapper;

    @Test
    public void testSelectByCondition(){
        List<Game> gameList=gameMapper.selectByCondition
                ("合金",null,null,null,null,null,"time_platform");

        log.info(gameList.toString());
    }

    @Test
    public void testSelectById(){
        Game game=gameMapper.selectById(620);

        log.info(game.toString());
    }

    @Test
    public void testDeleteByIds(){
        List<Integer> ids=new ArrayList<>();
        ids.add(621);
        ids.add(622);

        gameMapper.deleteByIds(ids);
    }

    @Test
    public void testInsertGame(){
        Game game=new Game(null,"测试","Test","PC/Switch","2023-05-05(PC)",
                "测试/游戏王","暴走d姜撞奶","熬夜毕设","testCover","testImg","testVideo",
                "评测","测试/游戏王/加拿大/魔导师","testGameUrl");

        gameMapper.insertGame(game);
    }

    @Test
    public void testUpdateGame(){
        Game game=new Game(1030,"测试(小心翼神龙)","Test小心翼神龙","PC/Switch/小心翼神龙","2023-05-05(PC)",
                "测试/游戏王小心翼神龙","暴走d姜撞奶","熬夜毕设","testCover","testImg","testVideo",
                "评测","测试/游戏王/加拿大/魔导师/小心翼神龙","testGameUrl");

        gameMapper.updateGame(game);
    }



}
