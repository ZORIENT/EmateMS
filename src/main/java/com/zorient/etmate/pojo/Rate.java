package com.zorient.etmate.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rate {
    private Float rateAvg;//平均评分
    private Integer rateNum;//评分人数
    private Integer rate1;//一星评分人数
    private Integer rate2;//二星评分人数
    private Integer rate3;//三星评分人数
    private Integer rate4;//四星评分人数
    private Integer rate5;//五星评分人数
}
