package com.zorient.etmate.service;

import com.zorient.etmate.pojo.Film;
import com.zorient.etmate.pojo.PageBean;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface FilmService {
    PageBean selectByCondition(String filmName, String genres, String languages, String regions,
                               Integer releaseYear, String tags, Integer sortId , Integer page, Integer pageSize);

    Film selectById(Integer id);

    void deleteByIds(List<Integer> ids);

    void insertFilm(Film film);

    void updateFilm(Film film);

    PageBean getSimilarFilms(Integer id, int page, int pageSize);
}
