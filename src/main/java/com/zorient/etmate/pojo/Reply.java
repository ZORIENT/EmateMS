package com.zorient.etmate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    private Integer id;//回复的id
    private String avatar;//回复者的头像
    private String username;//回复者昵称
    private String itemName;//评论的电影、游戏、书籍名称
    private String reply;//评论的回复
    private String comment;//评论的内容
    private LocalDateTime updateTime;//回复的更新时间
}
