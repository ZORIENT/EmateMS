package com.zorient.etmate.mapper;

import com.zorient.etmate.pojo.Book;
import com.zorient.etmate.pojo.Film;
import com.zorient.etmate.pojo.Game;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

    List<Film> searchFilm(String keyword);

    List<Game> searchGame(String keyword);

    List<Book> searchBook(String keyword);
}
