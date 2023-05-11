package com.zorient.etmate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Integer id;//评论主键id
    private Integer userId;//评论者id
    private User user;//评论者详细信息

    private Integer itemId;//评论物品的id
    private Film film;//评论电影的详细信息
    private Game game;//评论游戏的详细信息
    private Book book;//评论书籍的详细信息

    private List<Comment> children;//子级评论

    private Short type;//说明：1 电影，2 游戏，3 书籍
    private Integer parentId;//父级评论的id，无父级评论则为0
    private String comment;//评论内容
    private Short score;//用户评分
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}
