package com.zorient.etmate.mapper;

import com.zorient.etmate.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StatsMapper {
    @Select("select count(*) from tb_film")
    Integer getFilmCount();

    @Select("select count(*) from tb_game")
    Integer getGameCount();

    @Select("select count(*) from tb_book")
    Integer getBookCount();

    @Select("select count(*) from tb_user")
    Integer getUserCount();

    List<Category> getFilmStats();

    List<Category> getGameStats();

    List<Category> getBookStats();
}
