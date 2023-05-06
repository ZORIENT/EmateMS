package com.zorient.etmate;

import com.zorient.etmate.mapper.UserMapper;
import com.zorient.etmate.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootTest
public class UserTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectByCondition(){
        List<User> userList=userMapper.selectByCondition(null,null,null,null);
        log.info(userList.toString());
    }

    @Test
    public void testSelectById(){
        User user=userMapper.selectById(1);
        log.info(user.toString());
    }

    @Test
    public void testDeleteByIds(){
        List<Integer> ids=new ArrayList<>();
        ids.add(2);
        ids.add(3);
        userMapper.deleteByIds(ids);
    }

    @Test
    public void testUpdateUser(){
        User user=new User(6,"huant@123.com","huant@123.com",(short)1,
                "123456","touxiang",(short)2,LocalDateTime.now(),LocalDateTime.now());

        userMapper.updateUser(user);
    }

    @Test
    public void testRegister(){
        User user=new User(null,"1957193956@qq.com","1957193956@qq.com",null,
                "123456789",null,(short)0, LocalDateTime.now(),LocalDateTime.now());

        userMapper.register(user);
    }

    @Test
    public void testLogin(){
        User user=new User(null,"1957193956@qq.com","1957193956@qq.com",null,
                "123456789",null,(short)0, null,null);

        User u=userMapper.login(user);
        log.info(u.toString());
    }

}












