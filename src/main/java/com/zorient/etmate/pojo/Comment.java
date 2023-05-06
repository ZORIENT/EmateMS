package com.zorient.etmate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;//评论主键id
    private Integer userId;//评论者id
    private User user;//评论者详细信息

    private Integer itemId;//评论物品的id
    private Short type;//说明：1 电影，2 游戏，3 书籍
    private Integer parentId;//父级评论的id，无父级评论则为0
    private String comment;//评论内容
    private Short score;//用户评分

}
