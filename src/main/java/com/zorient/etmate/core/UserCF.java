package com.zorient.etmate.core;

import com.zorient.etmate.pojo.Comment;

import java.util.*;
import java.util.stream.Collectors;

/*
 * 基于用户的协同过滤
 * */
public class UserCF {

    /**
     * @param userId 拟推荐用户id
     * @param list   所有用户评分数据
     * @return List<Integer> 推荐物品的id列表
     */
    public static List<Integer> recommend(Integer userId, List<Comment> list) {
        //按用户id对所有用户评分数据进行分组
        Map<Integer, List<Comment>> userMap = list.stream().collect(Collectors.groupingBy(Comment::getUserId));
        //获取其他用户与当前用户的相关系数
        Map<Integer, Double> userDisMap = CoreMath.computeNeighbor(userId, userMap, 0);

        if (userDisMap != null) {
            //获取与拟推荐用户关系最近的用户，userDisMap.values()的值越接近1表示两名用户的相似度越高
            double maxValue = Collections.max(userDisMap.values());
            Set<Integer> userIds = userDisMap.entrySet().stream().filter(e ->
                    e.getValue() == maxValue).map(Map.Entry::getKey).collect(Collectors.toSet());
            //取关系最近的用户
            Integer nearestUserId = userIds.stream().findAny().orElse(null);
            if (nearestUserId == null) {
                return Collections.emptyList();
            }
            //最近邻用户看过的电影，游戏，书籍列表
            List<Integer> neighborItems = userMap.get(nearestUserId).stream().map(Comment::getItemId).collect(Collectors.toList());
            //拟推荐用户看过的电影，游戏，书籍列表
            List<Integer> userItems = userMap.get(userId).stream().map(Comment::getItemId).collect(Collectors.toList());
            //找到最近邻看过，但是该用户没看过的电影
            neighborItems.removeAll(userItems);

            return neighborItems;
        } else {
            return null;
        }
    }

}
