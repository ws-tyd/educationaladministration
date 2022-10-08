package com.hngy.educationaladministration.config;

import com.hngy.educationaladministration.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    /**
     * 添加视图跳转
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/teacher/businessView").setViewName("teacher/showtable/businessView");
        registry.addViewController("/teacher/workloadView").setViewName("teacher/showtable/workloadView");
        registry.addViewController("/teacher/workloadEdit").setViewName("teacher/fillouttable/workloadEdit");
        registry.addViewController("/teacher/businessEdit").setViewName("teacher/fillouttable/businessEdit");
        registry.addViewController("/teacher/businessPrint").setViewName("teacher/table/business");
        registry.addViewController("/teacher/workloadPrint").setViewName("teacher/table/workload");
    }

    /**
     *  放行路径，不经过拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).excludePathPatterns(
                "/",
                "/login",
                "/admin/login",
                "/admin/exit",
                "/teacher/login",
                "/teacher/exit",
                "/student/login",
                "/student/exit",
                "/js/**",
                "/asserts/**",
                "/webjars/**",
                "/css/**",
                "/error/**",
                "/cs"
        );
    }


}
