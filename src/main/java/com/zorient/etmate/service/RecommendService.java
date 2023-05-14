package com.zorient.etmate.service;

import com.zorient.etmate.pojo.Book;
import com.zorient.etmate.pojo.Film;
import com.zorient.etmate.pojo.Game;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RecommendService {

    List<Film> userCfRecommendFilm(Integer userId);

    List<Film> itemCfRecommendFilm(Integer itemId);

    List<Game> userCfRecommendGame(Integer id);

    List<Book> userCfRecommendBook(Integer id);
}
