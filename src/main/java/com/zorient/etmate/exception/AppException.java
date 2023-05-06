package com.zorient.etmate.exception;

import lombok.Data;

@Data
public class AppException extends RuntimeException{
    private Integer code=500;
    private String msg="服务器端异常";

    /*
    * 传入一个枚举类型
    * */
    public AppException(AppExceptionCodeMsg appExceptionCodeMsg){
        this.code=appExceptionCodeMsg.getCode();
        this.msg=appExceptionCodeMsg.getMsg();
    }

    public AppException(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
