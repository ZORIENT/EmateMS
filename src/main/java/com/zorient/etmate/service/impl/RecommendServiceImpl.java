package com.zorient.etmate.service.impl;

import com.zorient.etmate.core.ItemCF;
import com.zorient.etmate.core.UserCF;
import com.zorient.etmate.mapper.BookMapper;
import com.zorient.etmate.mapper.CommentMapper;
import com.zorient.etmate.mapper.FilmMapper;
import com.zorient.etmate.mapper.GameMapper;
import com.zorient.etmate.pojo.Book;
import com.zorient.etmate.pojo.Comment;
import com.zorient.etmate.pojo.Film;
import com.zorient.etmate.pojo.Game;
import com.zorient.etmate.service.RecommendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private GameMapper gameMapper;
    @Autowired
    private BookMapper bookMapper;

    /*
     * 基于用户的协同过滤算法推荐相关电影
     * */
    @Override
    public List<Film> userCfRecommendFilm(Integer userId) {
        // 获取用户评分评论信息列表。
        // type=1，表示查询的是电影的评论评分信息，parentId=0表示查询的是父级评论信息（只有父级评论的评分信息才有效）
        List<Comment> filmCommentData = commentMapper.selectFilmComment(
                null, null, (short) 1, null, null, null, null, 0);

        List<Integer> recommendFilmIds = UserCF.recommend(userId, filmCommentData);

        // 如果recommendFilmIds为null，则表示该用户为新用户，直接推荐热门电影
        if(recommendFilmIds!=null){
            List<Film> recommendFilmList= filmMapper.getAllFilms()
                    .stream().filter(film -> recommendFilmIds.contains(film.getId()))
                    .collect(Collectors.toList());

            List<Film> hotFilmList=filmMapper.getHotFilms(14-recommendFilmList.size());

            recommendFilmList.removeAll(hotFilmList);
            recommendFilmList.addAll(hotFilmList);

            log.info("size():{}",recommendFilmList.size());

            return recommendFilmList;
        }else{
            // 返回14条热门电影信息
            return filmMapper.getHotFilms(14);
        }
    }

    /*
    * 基于用户的协同过滤算法推荐相关游戏
    * */
    @Override
    public List<Game> userCfRecommendGame(Integer userId) {
        // 获取用户评分评论信息列表。
        // type=2，表示查询的是游戏的评论评分信息，parentId=0表示查询的是父级评论信息（只有父级评论的评分信息才有效）
        List<Comment> gameCommentData = commentMapper.selectGameComment(
                null, null, (short) 2, null, null, null, null, 0);

        List<Integer> recommendGameIds = UserCF.recommend(userId, gameCommentData);

        // 如果recommendGameIds为null，则表示该用户为新用户，直接推荐热门游戏
        if(recommendGameIds!=null){
            List<Game> recommendGameList= gameMapper.getAllGames()
                    .stream().filter(game -> recommendGameIds.contains(game.getId()))
                    .collect(Collectors.toList());

            List<Game> hotGameList=gameMapper.getHotGames(14-recommendGameList.size());

            recommendGameList.removeAll(hotGameList);
            recommendGameList.addAll(hotGameList);

            log.info("size():{}",recommendGameList.size());

            return recommendGameList;
        }else{
            // 返回14条热门游戏信息
            return gameMapper.getHotGames(14);
        }
    }

    /*
    * 基于用户的协同过滤算法推荐相关书籍
    * */
    @Override
    public List<Book> userCfRecommendBook(Integer userId) {
        // 获取用户评分评论信息列表。
        // type=3，表示查询的是书籍的评论评分信息，parentId=0表示查询的是父级评论信息（只有父级评论的评分信息才有效）
        List<Comment> bookCommentData = commentMapper.selectBookComment(
                null, null, (short) 3, null, null, null, null, 0);

        List<Integer> recommendBookIds = UserCF.recommend(userId, bookCommentData);

        // 如果recommendFilmIds为null，则表示该用户为新用户，直接推荐热门书籍
        if(recommendBookIds!=null){
            List<Book> recommendBookList= bookMapper.getAllBooks()
                    .stream().filter(book -> recommendBookIds.contains(book.getId()))
                    .collect(Collectors.toList());

            List<Book> hotBookList=bookMapper.getHotBooks(14-recommendBookList.size());

            recommendBookList.removeAll(hotBookList);
            recommendBookList.addAll(hotBookList);

            log.info("size():{}",recommendBookList.size());

            return recommendBookList;
        }else{
            // 返回14条热门书籍信息
            return bookMapper.getHotBooks(14);
        }
    }


    /*
     * 基于物品的协同过滤推荐相关电影
     * */
    @Override
    public List<Film> itemCfRecommendFilm(Integer itemId) {
        List<Comment> filmCommentData = commentMapper.selectFilmComment(
                null, null, (short) 1, null, null, null, null, 0);

        List<Integer> recommendFilmIds = ItemCF.recommend(itemId, filmCommentData);

        return filmMapper.getAllFilms().stream()
                .filter(film -> recommendFilmIds.contains(film.getId()))
                .collect(Collectors.toList());

    }
}
