package com.zorient.etmate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/*
 * 电影实体类
 * */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    private Integer id; //电影主键id
    private String imdbId;//电影的IMDB
    private String filmName;//电影名称
    private String cover;//电影封面
    private String alias;//电影别名
    private String director;//电影导演
    private String actors;//演员列表
    private Float doubanScore;//豆瓣评分
    private String genres;//电影类型
    private String languages;//电影语言
    private Integer mins;//电影时长
    private String regions;//电影地区
    private LocalDate releaseDate;//上映时间
    private String storyline;//电影简介
    private Integer releaseYear;//上映年份
    private String tags;//电影标签
}
