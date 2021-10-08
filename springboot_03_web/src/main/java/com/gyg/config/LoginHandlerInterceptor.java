package com.gyg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录判断配置
 * @author gyg
 */
@Configuration
public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        登陆之后应该存在用户的session
        Object loginUser = request.getSession().getAttribute("loginUser");
//        如果获取到的session为空
        if (loginUser == null) {
            request.setAttribute("msg", "没有权限！请先登录！");
//            重定向到首页
            request.getRequestDispatcher("/index.html").forward(request, response);
            return false;
        }
        return true;

    }
}
