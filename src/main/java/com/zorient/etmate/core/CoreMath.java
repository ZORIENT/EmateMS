package com.zorient.etmate.core;

import com.zorient.etmate.pojo.Comment;
import org.assertj.core.util.Lists;

import java.util.*;
import java.util.stream.IntStream;

/*
 * 协同过滤算法
 * */
public class CoreMath {
    /**
     * 计算各用户间相关系数并排序，获取拟推荐用户的最邻用户
     *
     * @param id（当前拟推荐用户的id）
     * @param map（各用户对于物品的评分信息，根据用户id进行分组）
     * @param type                         （类型0基于用户推荐 1基于物品推荐）
     * @return Map<Integer, Double> <用户id,该用户与拟推荐用户间的相关系数>
     */
    public static Map<Integer, Double> computeNeighbor(Integer id, Map<Integer, List<Comment>> map, int type) {
        // distMap<用户id,与拟推荐用户间的相关系数>
        Map<Integer, Double> distMap = new TreeMap<>();
        // userItems是当前拟推荐用户的评分信息
        List<Comment> userItems = map.get(id);

        // 不是新用户，否则评论表中没有新用户的评分信息
        if (userItems != null) {
            // key是用户id，value是该用户对电影、游戏、书籍的评分列表
            map.forEach((key, value) -> {
                //排除当前拟推荐用户
                if (!key.equals(id)) {
                    //计算关系系数（coefficient）
                    double coefficient = relateDist(value, userItems, type);
                    distMap.put(key, coefficient);
                }
            });
            return distMap;
        } else {
            return null;
        }
    }

    /**
     * 计算两个用户序列间的相关系数
     *
     * @param xList（另一名用户的评分信息）
     * @param yList（拟推荐用户的评分信息）
     * @param type（类型0基于用户推荐    1基于物品推荐）
     * @return double（返回计算出的相关系数）
     */
    private static double relateDist(List<Comment> xList, List<Comment> yList, int type) {
        List<Double> xs = Lists.newArrayList();
        List<Double> ys = Lists.newArrayList();
        xList.forEach(x -> {
            yList.forEach(y -> {
                if (type == 0) {
                    if (x.getItemId().equals(y.getItemId())) {
                        // getScore()获取评分
                        xs.add((double) x.getScore());
                        ys.add((double) y.getScore());
                    }
                } else {
                    if (x.getUserId().equals(y.getUserId())) {
                        xs.add((double) x.getScore());
                        ys.add((double) y.getScore());
                    }
                }
            });
        });
        return getRelate(xs, ys);
    }

    /**
     * 方法描述: 皮尔森（pearson）相关系数计算
     *
     * @param xs x集合
     * @param ys y集合（拟推荐用户的数据集）
     * @Return double 皮尔森（pearson）相关系数
     */
    public static double getRelate(List<Double> xs, List<Double> ys) {
        int n = xs.size();
        //至少有两个元素才能进行皮尔森系数计算
        if (n < 2) {
            return 0D;
        }

        double Ex = xs.stream().mapToDouble(x -> x).sum();
        double Ey = ys.stream().mapToDouble(y -> y).sum();
        double Ex2 = xs.stream().mapToDouble(x -> Math.pow(x, 2)).sum();
        double Ey2 = ys.stream().mapToDouble(y -> Math.pow(y, 2)).sum();
        double Exy = IntStream.range(0, n).mapToDouble(i -> xs.get(i) * ys.get(i)).sum();
        double numerator = Exy - Ex * Ey / n;
        double denominator = Math.sqrt((Ex2 - Math.pow(Ex, 2) / n) * (Ey2 - Math.pow(Ey, 2) / n));

        if (denominator == 0) {
            return 0D;
        }

        return numerator / denominator;
    }
}

