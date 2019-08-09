package com.paytm.Interceptor;
/*
 * @author: aditya10.kumar
 * @created: 08/08/19
 */

import com.paytm.entity.User;
import com.paytm.services.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TokenValidatorFilter implements HandlerInterceptor
{
    @Autowired
    private LoginServiceImpl ls;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String path = ((HttpServletRequest) httpServletRequest).getRequestURI();
        System.out.println(path);

        //typecasting needed to access the getSession()method;
        HttpServletRequest request = (HttpServletRequest) httpServletRequest;
        HttpSession sess = request.getSession(false);
        if (sess != null)
        {
            String token = (String) sess.getAttribute("token");
            System.out.print("Inside Token Validator Interceptor :");
            System.out.println(token);

            //query fire
            int id = ls.findUserIdByTokenService(token);
            System.out.println(id);

            //query fire
            User u= ls.findUserByUserIdService(id);

            if (u.getEmail().equals(sess.getAttribute("email"))==false)
            {
                //login unsuccessful
                httpServletRequest.getRequestDispatcher("index.jsp").forward(httpServletRequest, httpServletResponse);
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception
    {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}