package com.zorient.etmate.core;

import com.zorient.etmate.pojo.Comment;

import java.util.*;
import java.util.stream.Collectors;

/*
 * 基于物品的协同过滤
 * */
public class ItemCF {

    /**
     * @param itemId 当前电影id
     * @param list   用户电影评分数据
     * @return List<Integer>
     */
    public static List<Integer> recommend(Integer itemId, List<Comment> list) {
        //按物品进行分组
        Map<Integer, List<Comment>> itemMap = list.stream().collect(Collectors.groupingBy(Comment::getItemId));
        //获取其他物品与当前物品的关系值
        Map<Integer, Double> itemDisMap = CoreMath.computeNeighbor(itemId, itemMap, 1);
        //获取关系最近物品
        double maxValue = Collections.max(itemDisMap.values());

        return itemDisMap.entrySet().stream().filter(e ->
                e.getValue() == maxValue).map(Map.Entry::getKey).collect(Collectors.toList());
    }
}

