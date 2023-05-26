package com.zorient.etmate.service.impl;

import com.github.pagehelper.Page;
import com.zorient.etmate.pojo.Collection;
import com.github.pagehelper.PageHelper;
import com.zorient.etmate.exception.AppException;
import com.zorient.etmate.exception.AppExceptionCodeMsg;
import com.zorient.etmate.mapper.CollectionMapper;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.service.CollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

    /*
     * 条件分页查询收藏信息
     * */
    @Override
    public PageBean selectByCondition(Integer userId, Integer collectionId, Short type, Integer page, Integer pageSize) {
        //使用pagehelper设置分页参数
        PageHelper.startPage(page, pageSize);
        List<Collection> collections = switch (type) {
            /*查询电影收藏*/
            case 1 -> collectionMapper.selectFilmCollection(userId, collectionId, type);
            /*查询游戏收藏*/
            case 2 -> collectionMapper.selectGameCollection(userId, collectionId, type);
            /*查询书籍收藏*/
            case 3 -> collectionMapper.selectBookCollection(userId, collectionId, type);
            default -> throw new AppException(AppExceptionCodeMsg.PARAM_ERROR);
        };

        Page<Collection> p= (Page<Collection>) collections;

        //结果封装PageBean
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        log.info("条件查询收藏：{}",pageBean);

        return pageBean;
    }

    /*
    * 用户添加收藏
    * */
    @Override
    public void insertCollection(Collection collection) {
        List<Collection> collections = switch (collection.getType()) {
            /*查询电影收藏*/
            case 1 -> collectionMapper.selectFilmCollection(collection.getUserId(), collection.getCollectionId(), (short)1);
            /*查询游戏收藏*/
            case 2 -> collectionMapper.selectGameCollection(collection.getUserId(), collection.getCollectionId(), (short)2);
            /*查询书籍收藏*/
            case 3 -> collectionMapper.selectBookCollection(collection.getUserId(), collection.getCollectionId(), (short)3);
            default -> throw new AppException(AppExceptionCodeMsg.PARAM_ERROR);
        };

        if(collections.size()>=1){
            throw new AppException(AppExceptionCodeMsg.COLLECTION_ALREADY_EXIST);
        }else{
            collection.setCreateTime(LocalDateTime.now());
            collection.setUpdateTime(LocalDateTime.now());

            collectionMapper.insertCollection(collection);
        }
    }

    /*
    * 用户取消收藏
    * */
    @Override
    public void deleteById(Integer id) {
        collectionMapper.deleteById(id);
    }
}
