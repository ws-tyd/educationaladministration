package com.hngy.educationaladministration.utils;

import com.hngy.educationaladministration.auth.Authority;
import com.hngy.educationaladministration.auth.Role;
import org.springframework.web.method.HandlerMethod;

/**
 * 用于检查 方法 或者  类  是否需要权限
 * 并和 拥有的权限做对比
 * 如果方法上有 ，则以方法的 优先
 */

public class HandlerUitl {

    public static boolean checkAuthority(Object handler, Role[] roles1){
            if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取方法上的注解
            Authority authority = handlerMethod.getMethod().getAnnotation(Authority.class);
            // 如果方法上的注解为空 则获取类的注解
            if (authority == null) {
                authority = handlerMethod.getMethod().getDeclaringClass().getAnnotation(Authority.class);
            }
            // 如果标记了注解，则判断权限
            if (authority != null) {
                Role[] roles = authority.roles();
                //如果 方法权限为 0 则通过
                if(roles.length==0){
                    return true;
                }

                //判断 拥有的权限 是否 符合 方法所需权限
                for(int i = 0; i < roles.length; i++){
                    for(int j = 0; j < roles1.length; j++){
                        if(roles[i]==roles1[j]){
//                            System.out.println("可以访问");
                            return true;
                        }
                    }
                }

            }
            return false;
        }
        return true;

    }

}
