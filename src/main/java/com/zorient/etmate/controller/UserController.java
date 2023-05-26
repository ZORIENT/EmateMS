package com.zorient.etmate.controller;

import com.zorient.etmate.anno.Log;
import com.zorient.etmate.pojo.PageBean;
import com.zorient.etmate.pojo.Result;
import com.zorient.etmate.pojo.User;
import com.zorient.etmate.service.UserService;
import com.zorient.etmate.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /*
     * 条件分页查询用户信息
     * */
    @GetMapping("/user")
    public Result selectByCondition(@RequestParam(required = false) String email,
                                    @RequestParam(required = false) String username,
                                    @RequestParam(required = false) Short gender,
                                    @RequestParam(required = false) Short privilege,
                                    @RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer pageSize) {

        log.info("email:{},username:{},gender:{},privilege:{},page:{},pagesize:{}",
                email, username, gender, privilege, page, pageSize);
        PageBean pageBean = userService.selectByCondition(email, username, gender, privilege, page, pageSize);

        return Result.success(pageBean);
    }

    /*
     * 根据id查询用户信息
     * */
    @GetMapping("/user/{id}")
    public Result selectById(@PathVariable Integer id) {
        log.info("查询的用户id为：{}", id);
        User user = userService.selectById(id);

        return Result.success(user);
    }

    /*
     * 批量删除用户信息
     * */
    @Log
    @DeleteMapping("/user/{ids}")
    public Result deleteByIds(@PathVariable List<Integer> ids) {
        log.info("批量删除用户：{}", ids);
        userService.deleteByIds(ids);

        return Result.success();
    }

    /*
     * 修改用户信息
     * */
    @Log
    @PutMapping("/user")
    public Result updateUser(@RequestBody User user) {
        log.info("修改的用户信息为：{}", user);
        userService.updateUser(user);

        return Result.success();
    }

    /*
     * 添加用户（注册接口）
     * */
    @PostMapping("/user/register")
    public Result register(@RequestBody User user) {
        log.info("用户注册信息：{}", user);
        userService.register(user);

        return Result.success();
    }

    /*
     * 用户登录
     * */
    @PostMapping("/user/login")
    public Result login(@RequestBody User user) {
        log.info("登录用户信息为：{}", user);
        User u = userService.login(user);

        //登陆成功，生成并下发令牌
        Map<String, Object> claims = new HashMap<>();

        claims.put("id", u.getId());
        claims.put("email", u.getEmail());
        claims.put("username", u.getUsername());
        claims.put("privilege", u.getPrivilege());

        String jwt = JwtUtils.generateJWT(claims);
        return Result.success(jwt);
    }

    /*
     * 封禁用户！
     * */
    @PostMapping("/user/ban/{id}")
    public Result banUser(@PathVariable Integer id) {
        userService.banUser(id);

        return Result.success();
    }


    /*
     * 解封用户
     * */
    @PostMapping("/user/disban/{id}")
    public Result disbanUser(@PathVariable Integer id) {
        userService.disbanUser(id);

        return Result.success();
    }


}











