package com.zorient.etmate.service;

import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    PageBean selectByCondition(String email, String username, Short gender, Short privilege, Integer page, Integer pageSize);

    User selectById(Integer id);

    void deleteByIds(List<Integer> ids);

    void register(User user);

    User login(User user);

    void updateUser(User user);

    void banUser(Integer id);

    void disbanUser(Integer id);
}
