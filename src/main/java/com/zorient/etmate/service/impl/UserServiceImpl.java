package com.zorient.etmate.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zorient.etmate.exception.AppException;
import com.zorient.etmate.exception.AppExceptionCodeMsg;
import com.zorient.etmate.mapper.UserMapper;
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
        PageHelper.startPage(page, pageSize);

        //执行查询
        List<User> userList = userMapper.selectByCondition(email, username, gender, privilege);
        Page<User> p = (Page<User>) userList;

        //结果封装PageBean
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        log.info("条件查询用户：{}", pageBean);

        return pageBean;
    }

    /*
     * 根据id查询用户信息
     * */
    @Override
    public User selectById(Integer id) {
        if (userMapper.selectById(id) == null) {
            // 当前用户不存在
            throw new AppException(AppExceptionCodeMsg.USER_NOT_EXIST);
        } else {
            return userMapper.selectById(id);
        }
    }

    /*
     * 批量删除用户信息
     * 不删用户！！！！采用封禁和解封的方式对用户进行管理
     * */
    @Override
    public void deleteByIds(List<Integer> ids) {
        // 根据ids批量删除用户的收藏信息
        // 根据ids批量删除用户的评论数据
        // 1. 删除用户的评论信息（获取当前用户的评论ids）
        // 2. 根据评论ids批量删除评论
        // 3. 根据ids批量删除回复

        userMapper.deleteByIds(ids);
    }

    /*
     * 修改用户信息
     * */
    @Override
    public void updateUser(User user) {
        // 当前用户是否存在
        if (userMapper.selectById(user.getId()) == null) {
            // 当前用户不存在
            throw new AppException(AppExceptionCodeMsg.USER_NOT_EXIST);
        } else {
            // 用户存在，修改updateTime
            user.setUpdateTime(LocalDateTime.now());

            userMapper.updateUser(user);
        }
    }

    /*
     * 注册
     * */
    @Override
    public void register(User user) {
        // 当前邮箱是否已被注册
        if (userMapper.selectByEmail(user) == null) {
            // 当前邮箱未被注册
            user.setCreateTime(LocalDateTime.now());
            user.setUpdateTime(LocalDateTime.now());
            // 默认头像
            user.setAvatar("https://web-zorient.oss-cn-beijing.aliyuncs.com/0585bd48-62cd-4477-a6ed-5e7a570fafba.jpg");

            userMapper.register(user);
        } else {
            // 当前邮箱已被注册
            throw new AppException(AppExceptionCodeMsg.EMAIL_ALREADY_EXIST);
        }
    }

    /*
     * 用户登录
     * */
    @Override
    public User login(User user) {
        // 当前用户是否存在（当前邮箱是否已被注册）
        if (userMapper.selectByEmail(user) == null) {
            // 当前用户不存在
            throw new AppException(AppExceptionCodeMsg.USER_NOT_EXIST);
        } else if (userMapper.login(user) == null) {
            // 用户存在，检查密码是否正确
            throw new AppException(AppExceptionCodeMsg.PASSWORD_ERROR);
        } else if (userMapper.login(user).getPrivilege() == 3) {
            throw new AppException(AppExceptionCodeMsg.USER_ALREADY_BANNED);
        } else {
            return userMapper.login(user);
        }
    }

    /*
     * 封禁用户！
     * */
    @Override
    public void banUser(Integer id) {
        if (userMapper.selectById(id) == null) {
            // 当前用户不存在
            throw new AppException(AppExceptionCodeMsg.USER_NOT_EXIST);
        } else {
            // 用户存在，更新修改用户头像、权限位、用户昵称等
            User user = new User(id, null, "账户已封禁", null, null,
                    "https://web-zorient.oss-cn-beijing.aliyuncs.com/06b9997c-0836-4fc2-ba24-81bec4106d67.png",
                    (short) 3, null, LocalDateTime.now());

            userMapper.updateUser(user);
        }
    }

    /*
    * 解封用户！
    * */
    @Override
    public void disbanUser(Integer id) {
        if (userMapper.selectById(id) == null) {
            // 当前用户不存在
            throw new AppException(AppExceptionCodeMsg.USER_NOT_EXIST);
        } else {
            // 用户存在，权限修改为普通用户
            User user = new User(id, null, "账户已解封", null, null,
                    null,(short) 2, null, LocalDateTime.now());

            userMapper.updateUser(user);
        }
    }
}
