package com.hngy.educationaladministration.interceptor;

import com.hngy.educationaladministration.auth.Role;
import com.hngy.educationaladministration.config.WebExceptionHandler;
import com.hngy.educationaladministration.entity.User;
import com.hngy.educationaladministration.utils.HandlerUitl;
import com.hngy.educationaladministration.utils.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebExceptionHandler.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String url = request.getRequestURI();
//        log.info(request.getMethod()+" 请求URL："+url);

        //从Token中解析User信息
        User user = TokenUtil.verifyToken(request);

        String contextPath = request.getContextPath();
        //user 为空则 表示 Token 不存在
        if (user != null) {
            if (user.getRole().equals("sadmin")) {
                //检查方法上 是否有注解的 Role.SADMIN 或者 Role.ADMIN 权限 , 没有则检查类上有没有 如果符合要求则放行
                if (HandlerUitl.checkAuthority(handler, new Role[]{Role.SADMIN, Role.ADMIN})) {
                    request.setAttribute("user", user);
                    return true;
                }
            }
            if (user.getRole().equals("admin")) {
                if (HandlerUitl.checkAuthority(handler, new Role[]{Role.ADMIN})) {
                    request.setAttribute("user", user);
                    return true;
                }else {
                    response.sendRedirect(contextPath + "/login");
                }
            }


            if (user.getRole().equals("teacher")) {
                if (HandlerUitl.checkAuthority(handler, new Role[]{Role.TEACHER})) {

                    return true;
                } else {
                    response.sendRedirect(contextPath + "/login");
                }
            }

            if (user.getRole().equals("student")) {
                if (HandlerUitl.checkAuthority(handler, new Role[]{Role.STUDENT})) {

                    return true;
                } else {

                    response.sendRedirect(contextPath + "/student/login");
                }
            }
        }else {
            response.sendRedirect(contextPath + "/login");
        }


        return false;
    }
}
