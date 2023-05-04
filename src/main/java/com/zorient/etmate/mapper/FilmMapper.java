package com.zorient.etmate.mapper;

import com.zorient.etmate.pojo.Film;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface FilmMapper {
    List<Film> selectByCondition(String filmName, String genres, String languages, String regions,
                                 Integer releaseYear, String tags,String sort);

    @Select("select id, imdb_id, film_name, cover, alias, director, actors, " +
            "douban_score, genres, languages, mins, regions, release_date, storyline," +
            " release_year, tags from tb_film where id = #{id}")
    Film selectById(Integer id);

    void deleteByIds(List<Integer> ids);

    void insertFilm(Film film);

    void updateFilm(Film film);
}
