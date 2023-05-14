package com.zorient.etmate;

import com.zorient.etmate.pojo.Film;
import com.zorient.etmate.service.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class RecommendTest {
    @Autowired
    private RecommendService recommendService;

    @Test
    public void testRecommendFilms(){
        List<Film> filmList=recommendService.userCfRecommendFilm(9);

        log.info("@@@@@@:{}",filmList.toString());
    }
}
