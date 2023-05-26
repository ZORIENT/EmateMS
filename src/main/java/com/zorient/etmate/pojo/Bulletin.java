package com.zorient.etmate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bulletin {
    private Integer id;//公告id
    private Integer userId;//公告发布者id

    private String avatar;//发布公告作者头像
    private String username;//发布公告作者昵称
    private String email;//电子邮箱

    private String title;//公告标题
    private String content;//公告内容
    private String image;//公告图片
    private LocalDateTime createTime;//公告发布时间
    private LocalDateTime updateTime;//公告修改时间
}
