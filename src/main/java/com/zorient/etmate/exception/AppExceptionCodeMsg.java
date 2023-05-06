package com.zorient.etmate.exception;

public enum AppExceptionCodeMsg {
    EMAIL_ALREADY_EXIST(1000,"该邮箱已被注册"),
    NO_LOGIN(1001,"尚未登录，请登录"),
    LOGIN_FAIL(1002,"邮箱或密码错误，请重试"),
    PARAM_ERROR(1003,"参数异常")

    ;

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    AppExceptionCodeMsg(Integer code,String msg){
        this.code=code;
        this.msg=msg;
    }
}
