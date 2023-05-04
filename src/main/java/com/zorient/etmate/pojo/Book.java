package com.zorient.etmate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    private Integer id;//书籍主键id
    private String ibsn;//书籍的ibsn码
    private String bookName;//书籍名称
    private String author;//书籍作者
    private String translator;//书籍译者
    private String publisher;//书籍出版商
    private LocalDate publicationTime;//出版时间
    private Float price;//价格
    private Float doubanScore;//豆瓣评分
    private Integer pages;//书籍页数
    private String introduction;//书籍简介
    private String authorIntro;//作者简介
    private String tags;//书籍标签
    private String cover;//书籍封面
}
