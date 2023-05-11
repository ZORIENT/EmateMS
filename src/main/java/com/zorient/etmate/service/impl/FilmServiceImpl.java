package com.zorient.etmate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zorient.etmate.mapper.FilmMapper;
import com.zorient.etmate.pojo.Film;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmMapper filmMapper;

    /*
    * 根据条件查询电影信息
    * */
    @Override
    public PageBean selectByCondition(String filmName, String genres, String languages, String regions,
                                      Integer releaseYear, String tags, Integer sortId , Integer page, Integer pageSize) {

        String  sort=new String("douban_score");
        switch(sortId){
            case 1:
                sort="douban_score";
                break;
            case 2:
                sort="mins";
                break;
            default:
                sort="release_date";
                break;
        }

        log.info("sort的值：{}",sort);

        //使用pagehelper设置分页参数
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<Film> filmList=filmMapper.selectByCondition(filmName,genres,languages,regions,releaseYear,tags,sort);
        Page<Film> p= (Page<Film>) filmList;

        //结果封装PageBean
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        log.info("条件查询电影：{}",pageBean);

        return pageBean;
    }


    /*
    * 根据id查询电影信息
    * */
    @Override
    public Film selectById(Integer id) {
        Film film=filmMapper.selectById(id);

        return film;
    }

    /*
    * 根据id批量删除电影信息
    * */
    @Override
    public void deleteByIds(List<Integer> ids) {
        filmMapper.deleteByIds(ids);
    }

    /*
    * 添加电影信息
    * */
    @Override
    public void insertFilm(Film film) {
        filmMapper.insertFilm(film);
    }

    /*
    * 更新电影信息
    * */
    @Override
    public void updateFilm(Film film) {
        filmMapper.updateFilm(film);
    }

    /*
    * 根据电影id推荐相关电影
    * */
    @Override
    public PageBean getSimilarFilms(Integer id, int page, int pageSize) {
        Film film=filmMapper.selectById(id);

        String[] actor=film.getActors().split("/");
        String[] tag=film.getTags().split("/");

        //使用pagehelper设置分页参数
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<Film> filmList=filmMapper.getSimilarFilms(id,actor[0],tag[0],film.getDirector());
        Page<Film> p= (Page<Film>) filmList;

        //结果封装PageBean
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        log.info("条件查询电影：{}",pageBean);

        return pageBean;
    }
}
