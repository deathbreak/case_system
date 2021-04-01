package com.abc.case_system.interceptors;

import com.abc.case_system.bean.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        User fl = (User) request.getSession().getAttribute("login");
        if (fl != null) {
            String check_string = request.getRequestURI();
            if(check_string.length()<=5){
                response.sendRedirect("/login");
                return false;
            }else {
                if ((fl.getRole()==0&&"/user".equals(check_string.substring(0,5)))||(fl.getRole()==1&&"/admin".equals(check_string.substring(0,6)))||("/error".equals(check_string.substring(0,6)))){
                    return true;
                }else{
                    response.sendRedirect("/login");
                    return false;
                }
            }
        } else {
            response.sendRedirect("/login");
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
