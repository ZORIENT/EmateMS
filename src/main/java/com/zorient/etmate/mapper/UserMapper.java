package com.zorient.etmate.mapper;

import com.zorient.etmate.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> selectByCondition(String email, String username, Short gender, Short privilege);

    @Select("select id, email, username, gender, password," +
            " avatar,privilege, create_time, update_time " +
            "from tb_user where id=#{id}")
    User selectById(Integer id);

    void deleteByIds(List<Integer> ids);

    void register(User user);

    @Select("select id, email, username, gender, password, avatar, privilege, create_time, update_time " +
            "from tb_user where email=#{email} and password=#{password}")
    User login(User user);

    void updateUser(User user);
}
