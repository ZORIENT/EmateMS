package com.zorient.etmate.mapper;

import com.zorient.etmate.pojo.Game;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GameMapper {

    List<Game> selectByCondition(String gameName, String platforms, Integer releaseYear, String genres, String publisher, String tags, String sort);

    @Select("select id, game_name, game_name_en, platforms, time_platform, genres," +
            " publisher, introduce, cover, img, video, evalute, tags, game_url " +
            "from tb_game where id=#{id}")
    Game selectById(Integer id);

    void deleteByIds(List<Integer> ids);

    void insertGame(Game game);

    void updateGame(Game game);

    List<Game> getSimilarGames(Integer id, String tag, String publisher, String genres);
}
