package com.zorient.etmate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer id;//用户主键id
    private String email;//用户邮箱
    private String username;//用户昵称
    private Short gender;//性别：1 男，2 女
    private String password;//密码，MD5加密（固定32位）
    private String avatar;//用户头像
    private Short privilege;//权限：1 管理员，2 普通用户
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
