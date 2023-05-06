package com.zorient.etmate.pojo;

/*
 * 前后端交互统一响应结果Result
 * */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;//响应码，1 成功，0 失败
    private String msg;//响应信息，描述字符串
    private Object data;//返回的数据对象

    /*
    * 响应成功
    * */
    public static Result success() {//增删改，成功响应
        return new Result(1, "Success!", null);
    }

    public static Result success(Object data) {//查询成功，返回数据对象
        return new Result(1, "Success!", data);
    }

    /*
     * 响应错误
     * */
    public static Result error(String msg) {//响应失败
        return new Result(0, msg, null);
    }

    public static Result error(Integer code, String msg) {//自定义异常
        return new Result(code, msg, null);
    }
}










