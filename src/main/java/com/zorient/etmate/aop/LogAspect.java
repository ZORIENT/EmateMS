package com.zorient.etmate.aop;

import com.alibaba.fastjson.JSONObject;
import com.zorient.etmate.mapper.OperateLogMapper;
import com.zorient.etmate.pojo.OperateLog;
import com.zorient.etmate.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect//切面类
@Component
@Slf4j
public class LogAspect {
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.zorient.etmate.anno.Log)")
    public Object recordLog(ProceedingJoinPoint joinPoint) throws Throwable {
        //获取操作人的id（当前登录员工的信息）
        //获取请求头中的令牌JWT，解析令牌
        String jwt = request.getHeader("token");

        Claims claims = JwtUtils.parseJWT(jwt);
        Integer operateUser = (Integer) claims.get("id");

        //当前操作时间
        LocalDateTime operateTime = LocalDateTime.now();

        //操作类的类名
        String className = joinPoint.getTarget().getClass().getName();

        //操作方法名
        String methodName = joinPoint.getSignature().getName();

        //获取方法参数
        Object[] args = joinPoint.getArgs();
        String methodParams = Arrays.toString(args);

        //记录方法运行的开始时间
        Long begin = System.currentTimeMillis();

        //调动原始目标运行...............
        Object result = joinPoint.proceed();

        //记录操作结束时间
        Long end = System.currentTimeMillis();

        //获取方法返回值
        String returnValue = JSONObject.toJSONString(result);

        //获取操作耗时
        Long costTime = end - begin;

        //记录操作日志
        OperateLog operaterLog = new OperateLog(null, operateUser, operateTime,
                className, methodName, methodParams, returnValue, costTime);

        operateLogMapper.insert(operaterLog);

        log.info("AOP记录操作日志：{}",operaterLog);

        return result;
    }

}
