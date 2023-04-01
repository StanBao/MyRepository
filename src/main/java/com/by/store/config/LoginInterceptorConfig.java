package com.by.store.config;

import com.by.store.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/** 拦截器的注册  不注册不生效  且需要设置哪些页面不需要拦截  哪些需要拦截*/
@Configuration
public class LoginInterceptorConfig  implements WebMvcConfigurer {


    /** 配置拦截器的方法*/
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义拦截器对象
        HandlerInterceptor interceptor = new LoginInterceptor();
        //配置白名单  存在list集合中
        List<String> patterns = new ArrayList<>();
        patterns.add("/bootstrap3/**");
        patterns.add("/css/**");
        patterns.add("/images/**");
        patterns.add("/js/**");
        patterns.add("/web/register.html");
        patterns.add("/web/product.html");
        patterns.add("/web/login.html");
        patterns.add("/web/index.html");
        patterns.add("/users/reg");
        patterns.add("/users/login");
        patterns.add("/districts/**");
        patterns.add("/products/**");



        //这样就完成了拦截器的注册
        registry.addInterceptor(interceptor)
                .addPathPatterns("/**")  //哪些拦截   /**代表所有都拦截
                .excludePathPatterns(patterns); // exclude是设定哪些不拦截  就是白名单  以集合形式存储
    }
}
