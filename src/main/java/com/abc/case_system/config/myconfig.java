package com.abc.case_system.config;


import com.abc.case_system.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class myconfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    public void addInterceptors(InterceptorRegistry registry){
        // addPathPatterns("/**") 表示拦截所有的请求，
        // excludePathPatterns("/login", "/register") 表示除了登陆与注册之外，因为登陆注册不需要登陆也可以访问  ,"/static/**"
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns("/login", "/register", "/css/**", "/js/**", "/images/**");
    }
}