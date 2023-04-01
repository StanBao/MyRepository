package com.by.store.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginInterceptor implements HandlerInterceptor {

    /**
     *定义一个拦截器  检测全局session对象中是否有uid数据 如果有就放行  如果没有就调转登陆页面 login.html
     * @param request  请求对象
     * @param response  响应对象
     * @param handler   处理器 url+controller 映射
     * @return  true 放行   false拦截
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //httpServletRequest对象来获取session对象
        Object obj = request.getSession().getAttribute("uid");

        if (obj==null){
            response.sendRedirect("/web/login.html");
            return false;
        }
        //放行
        return true;
    }
}
