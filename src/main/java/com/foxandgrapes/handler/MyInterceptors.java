package com.foxandgrapes.handler;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyInterceptors implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果存在userId（已登陆或注册）则返回true（此时不为空）
        boolean login = request.getSession().getAttribute("userId") != null;
        // 没登录则返回index.jsp
        if (!login) {
            request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
        return login;
    }
}
