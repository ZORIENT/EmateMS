package com.zorient.etmate.service;

import com.zorient.etmate.pojo.Game;
import com.zorient.etmate.pojo.PageBean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GameService {
    PageBean selectByCondition(String gameName, String platforms, Integer releaseYear, String genres, String publisher, String tags, Integer sortId, Integer page, Integer pageSize);

    Game selectById(Integer id);

    void deleteByIds(List<Integer> ids);

    void insertGame(Game game);

    void updateGame(Game game);
}
