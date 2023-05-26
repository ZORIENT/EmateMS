package com.zorient.etmate.config;

import com.zorient.etmate.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration//配置类
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*
         * 1."/*"，一级路径
         * 2."/**"，任意级路径
         * 3."/depts/*"，/depts下的一级路径
         * 4."/depts/**"，/depts下的任意级路径
         * */
        //拦截所有，，，除了excludePathPatterns里面的地址
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/",
                        "/favicon.ico",
                        "/index.html",    //Spring Boot默认使用http://localhost:端口号就能够访问到/index.html,所以要把它忽略掉
                        "/js/**",        //忽略掉js请求
                        "/img/**",        //忽略掉img请求，这里放的是页面需要使用到的固定的图片，像什么error.png,loading.gif之类的
                        "/css/**",        //忽略掉css请求
                        "/fonts/**",
                        "/user/login",
                        "/user/register");
    }

    /*
     * 解决跨域问题
     * */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有接口
                .allowCredentials(true) // 是否发送 Cookie
                .allowedOriginPatterns("*") // 支持域
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 支持方法
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}
