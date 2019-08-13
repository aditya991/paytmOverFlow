package com.paytm.Interceptor;
/*
 * @author: aditya10.kumar
 * @created: 08/08/19
 */

import com.paytm.services.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor
{
    @Autowired
    private LoginServiceImpl ls;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String email = httpServletRequest.getParameter("email");
        String password = httpServletRequest.getParameter("password");
        System.out.println("Inside Login Interceptor");

        try
        {
            boolean flag = ls.UserAuthenticationService(email, password);
            System.out.println("Flag is "+flag+". That email doesn't exist.");
            if (!flag)
            {
                httpServletRequest.getRequestDispatcher("index.jsp").forward(httpServletRequest, httpServletResponse);
                return false;
            }
        }
        catch(Exception e)
        {
            httpServletRequest.getRequestDispatcher("index.jsp").forward(httpServletRequest, httpServletResponse);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {}
}
