package com.zorient.etmate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stats {
    private Integer filmCount;//电影数量
    private Integer gameCount;//游戏数量
    private Integer bookCount;//书籍数量
    private Integer userCount;//用户数量
}
