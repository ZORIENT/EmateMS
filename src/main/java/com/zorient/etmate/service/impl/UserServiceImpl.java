package com.zorient.etmate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zorient.etmate.mapper.UserMapper;
import com.zorient.etmate.pojo.Book;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.pojo.User;
import com.zorient.etmate.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /*
    * 条件分页查询用户信息
    * */
    @Override
    public PageBean selectByCondition(String email, String username, Short gender, Short privilege, Integer page, Integer pageSize) {
        //使用pagehelper设置分页参数
        PageHelper.startPage(page,pageSize);

        //执行查询
        List<User> userList=userMapper.selectByCondition(email,username,gender,privilege);
        Page<User> p= (Page<User>) userList;

        //结果封装PageBean
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        log.info("条件查询用户：{}",pageBean);

        return pageBean;
    }

    /*
    * 根据id查询用户信息
    * */
    @Override
    public User selectById(Integer id) {
        User user=userMapper.selectById(id);

        return user;
    }

    /*
    * 批量删除用户信息
    * */
    @Override
    public void deleteByIds(List<Integer> ids) {
        userMapper.deleteByIds(ids);
    }

    /*
    * 修改用户信息
    * */
    @Override
    public void updateUser(User user) {
        //修改updateTime
        user.setUpdateTime(LocalDateTime.now());

        userMapper.updateUser(user);
    }

    /*
    * 注册
    * */
    @Override
    public void register(User user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());

        userMapper.register(user);
    }

    /*
    * 用户登录
    * */
    @Override
    public User login(User user) {
       return userMapper.login(user);
    }

}
