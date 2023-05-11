package com.zorient.etmate;

import com.zorient.etmate.mapper.FilmMapper;
import com.zorient.etmate.pojo.Film;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
public class FilmTest {
    @Autowired
    private FilmMapper filmMapper;

    @Test
    public void testSelectByCondition(){
        List<Film> filmList=filmMapper.selectByCondition("霸王",null,null,null,null,null,"douban_score");

        log.info(filmList.toString());
    }

    @Test
    public void testSelectById(){
        Film film =filmMapper.selectById(1);

        log.info(film.toString());
    }

    @Test
    public void testDeleteByIds(){
        List<Integer> ids=new ArrayList<>();
        ids.add(2);
        ids.add(3);

        filmMapper.deleteByIds(ids);
    }

    @Test
    public void testInsertFilm(){
        Film film=new Film(null,"imdbtest","插入电影测试","12345","测试","暴走d姜撞奶","测试",(float)6.5,"类型/测试",
                "汉语",125,"大陆", LocalDate.now(),"简介",2023,"测试/熬夜/秃头");

        filmMapper.insertFilm(film);
    }

    @Test
    public void testUpdateFilm(){
        Film film=new Film(9012,"imdbte22","插入电影测试(李连杰)","12345","测试","暴走d姜撞奶","测试",(float)6.5,"类型/测试",
                "汉语",125,"大陆", LocalDate.now(),"简介",2023,"测试/熬夜/秃头");

        filmMapper.updateFilm(film);
    }

    @Test
    public void testGetSimilarFilms(){
//        List<Film> films=filmMapper.getSimilarFilms(1,"霸王","陈凯歌","普通话");
//
//        log.info(films.toString());

        Film film=filmMapper.selectById(1);

        String[]  tags=film.getTags().split("/");

        for(int i=0;i<tags.length;i++){
            log.info(tags[i]);
        }
    }
}
