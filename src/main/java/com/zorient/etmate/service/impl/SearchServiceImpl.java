package com.zorient.etmate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zorient.etmate.mapper.SearchMapper;
import com.zorient.etmate.pojo.*;
import com.zorient.etmate.service.SearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchMapper searchMapper;

    /*
    * 根据关键词搜索相关电影，游戏，书籍
    * */
    @Override
    public SearchResult search(String keyword,Integer page,Integer pageSize) {
        SearchResult searchResult=new SearchResult();

        /*查询相关电影*/
        //执行查询
        //使用pagehelper设置分页参数
        PageHelper.startPage(page,pageSize);
        List<Film> filmList=searchMapper.searchFilm(keyword);
        Page<Film> p1= (Page<Film>) filmList;
        //结果封装PageBean
        PageBean filmResult=new PageBean(p1.getTotal(),p1.getResult());
        searchResult.setFilmResults(filmResult);

        /*查询相关游戏*/
        //执行查询
        //使用pagehelper设置分页参数
        PageHelper.startPage(page,pageSize);
        List<Game> gameList=searchMapper.searchGame(keyword);
        Page<Game> p2= (Page<Game>) gameList;
        //结果封装PageBean
        PageBean gameResult=new PageBean(p2.getTotal(),p2.getResult());
        searchResult.setGameResults(gameResult);

        /*查询相关书籍*/
        //执行查询
        //使用pagehelper设置分页参数
        PageHelper.startPage(page,pageSize);
        List<Book> bookList=searchMapper.searchBook(keyword);
        Page<Book> p3= (Page<Book>) bookList;
        //结果封装PageBean
        PageBean bookResult=new PageBean(p3.getTotal(),p3.getResult());
        searchResult.setBookResults(bookResult);

        return searchResult;
    }
}












