package com.zorient.etmate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection {
    private Integer id;//收藏主键id
    private Integer userId;//收藏者id
    private Integer collectionId;//收藏品id

    private Film film;//电影收藏信息
    private Game game;//游戏收藏信息
    private Book book;//书籍信息信息

    private Short type;//收藏品类型，说明：1 电影，2 游戏，3 书籍
    private LocalDateTime createTime;//收藏时间
    private LocalDateTime updateTime;//更新时间


}
