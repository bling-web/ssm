package com.cc.springMVC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

/**
 * springMVC拦截器实现类
 */

public class Intercepte implements HandlerInterceptor {
    /**
     * 对访问进行拦截,判断有没有登录成功的标志
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean flag=false;
        Enumeration names = request.getSession().getAttributeNames();
        while(names.hasMoreElements()){
            if(names.nextElement()=="login_success"){
                boolean login_success = (boolean)request.getSession().getAttribute("login_success");
                if(login_success){
                    flag=true;
                }
            }
        }
        if(!flag){
            request.getRequestDispatcher("/login/to").forward(request,response);
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
