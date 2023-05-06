package com.zorient.etmate.exception;


import com.zorient.etmate.pojo.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)//捕获所有异常
    public Result ex(Exception ex){
        //判断拦截的异常是否是自定义的异常
        if(ex instanceof AppException){
            AppException appException=(AppException) ex;
            return Result.error(appException.getCode(),appException.getMsg());
        }

        //如果不是自定义的异常
        return Result.error("服务器端异常");

        /*
        * 使用：throw new AppException(AppExceptionCodeMsg.EMAIL_ALREADY_EXIST);
        * */
    }




}
