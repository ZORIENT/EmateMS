package com.zorient.etmate.exception;

public enum AppExceptionCodeMsg {
    NOT_LOGIN(1000,"尚未登录，请登录！"),
    EMAIL_ALREADY_EXIST(1001,"当前邮箱已被注册！"),
    USER_NOT_EXIST(1002,"当前用户不存在！"),
    PASSWORD_ERROR(1003,"密码输入错误！"),
    USER_ALREADY_BANNED(1004,"当前用户已封禁！"),
    RESOURCE_NOT_AVAILABLE(1005,"所选资源不存在！"),
    PARAM_ERROR(1006,"参数异常！"),
    COLLECTION_ALREADY_EXIST(1007,"该内容已收藏！")
    ;

    private final Integer code;
    private final String msg;

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
