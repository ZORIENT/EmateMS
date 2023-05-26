package com.zorient.etmate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zorient.etmate.exception.AppException;
import com.zorient.etmate.exception.AppExceptionCodeMsg;
import com.zorient.etmate.mapper.CollectionMapper;
import com.zorient.etmate.mapper.CommentMapper;
import com.zorient.etmate.mapper.FilmMapper;
import com.zorient.etmate.pojo.Film;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class FilmServiceImpl implements FilmService {
    @Autowired
    private FilmMapper filmMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private CollectionMapper collectionMapper;

    /*
    * 根据条件查询电影信息
    * */
    @Override
    public PageBean selectByCondition(String filmName, String genres, String languages, String regions,
                                      Integer releaseYear, String tags, Integer sortId , Integer page, Integer pageSize) {

        String  sort = switch (sortId) {
            case 1 -> "douban_score";
            case 2 -> "mins";
            case 3 -> "release_date";
            default -> throw new AppException(AppExceptionCodeMsg.PARAM_ERROR);
        };

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

        if(film!=null){
            // 结果不为空
            return film;
        }else{
            throw new AppException(AppExceptionCodeMsg.RESOURCE_NOT_AVAILABLE);
        }
    }

    /*
    * 根据ids批量删除电影信息
    * */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByIds(List<Integer> ids) {
        // 删除相关评论和收藏
        for(Integer id:ids){
            commentMapper.deleteCommentsByItemId(id,(short)1);
            collectionMapper.deleteCollectionsByCollectionId(id,(short)1);
        }

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
