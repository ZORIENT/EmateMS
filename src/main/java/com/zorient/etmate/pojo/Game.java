package com.zorient.etmate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
    private Integer id;//游戏主键id
    private String gameName;//游戏中文名
    private String gameNameEn;//游戏英文名
    private String platforms;//游戏平台（PC，PS4，PS5，Switch等）
    private String timePlatform;//首次发布时间+平台
    private String genres;//游戏类型
    private String publisher;//游戏发行商
    private String introduce;//游戏简介
    private String cover;//游戏封面
    private String img;//游戏截图
    private String video;//游戏预告片
    private String evalute;//测评信息
    private String tags;//游戏标签
    private String gameUrl;//游戏在3DM的链接
}
