package com.zorient.etmate.interceptor;

import com.zorient.etmate.exception.AppException;
import com.zorient.etmate.exception.AppExceptionCodeMsg;
import com.zorient.etmate.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override//目标资源方法运行前运行，返回true：放行；返回false：不放行。
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//        System.out.println("preHandler...");
        //1.获取请求url
//        String url = request.getRequestURL().toString();
        //2.获取请求中的token
        String jwt = request.getHeader("token");
        //3.判断获取的token是否为空，如果为空则拦截
        if (!StringUtils.hasLength(jwt)) {
                if(request.getMethod().equals("OPTIONS")){
                    log.info("OPTIONS请求，放行");
                    return true;
                }else{
                    log.info("请求头token为空，返回未登录的信息");
                    throw new AppException(AppExceptionCodeMsg.NOT_LOGIN);
                }


//                log.info("请求头token为空，返回未登录的信息");
//                Result error = Result.error("NOT_LOGIN");
//
//                //手动转换
//                String notLogin = JSONObject.toJSONString(error);
//                response.getWriter().write(notLogin);
        }

        //4.校验jwt令牌是否合法
        try {
            JwtUtils.parseJWT(jwt);
        } catch (Exception e) {
            e.printStackTrace();
            /*log.info("解析令牌失败，返回未登录的错误信息");
            Result error = Result.error("NOT_LOGIN");
            //手动转换
            String notLogin = JSONObject.toJSONString(error);
            response.getWriter().write(notLogin);
            return false;*/
            throw new AppException(AppExceptionCodeMsg.NOT_LOGIN);
        }

        //5.放行
        log.info("令牌合法，放行");
        return true;
    }

    /* @Override//目标资源运行后运行
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandler...");
    }*/

    /*@Override//视图渲染完成之后运行，最后运行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        System.out.println("afterHandler...");
    }*/
}
