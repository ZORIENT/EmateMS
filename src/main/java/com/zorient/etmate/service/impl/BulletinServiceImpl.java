package com.zorient.etmate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zorient.etmate.mapper.BulletinMapper;
import com.zorient.etmate.pojo.Book;
import com.zorient.etmate.pojo.Bulletin;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.service.BulletinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class BulletinServiceImpl implements BulletinService {
    @Autowired
    private BulletinMapper bulletinMapper;

    /*
    * 分页查询公告信息
    * */
    @Override
    public PageBean selectAllBulletin(Integer page, Integer pageSize) {
        //使用pagehelper设置分页参数
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<Bulletin> bulletinList=bulletinMapper.selectAllBulletin();
        Page<Bulletin> p= (Page<Bulletin>) bulletinList;

        //结果封装PageBean
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        log.info("条件查询书籍：{}",pageBean);

        return pageBean;
    }

    /*
    * 根据id查询公告信息
    * */
    @Override
    public Bulletin selectBulletinById(Integer id) {
        Bulletin bulletin=bulletinMapper.selectBulletinById(id);

        return bulletin;
    }

    @Override
    public void deleteBulletinByIds(List<Integer> ids) {
        bulletinMapper.deleteBulletinByIds(ids);
    }

    /*
    * 添加公告
    * */
    @Override
    public void insertBulletin(Bulletin bulletin) {
        bulletin.setCreateTime(LocalDateTime.now());
        bulletin.setUpdateTime(LocalDateTime.now());

        bulletinMapper.insertBulletin(bulletin);
    }

    /*
    * 更新公告信息
    * */
    @Override
    public void updateBulletin(Bulletin bulletin) {
        bulletin.setUpdateTime(LocalDateTime.now());

        bulletinMapper.updateBulletin(bulletin);
    }
}
