package com.paytm.Filter;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
import com.paytm.entity.User;
import com.paytm.services.LoginServiceImpl;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
public class TokenValidatorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        //typecasting needed to access the getSession()method;
        HttpServletRequest request=(HttpServletRequest) servletRequest;
        HttpSession sess=request.getSession(false);
        if(sess!= null)
        {
            String token= (String) sess.getAttribute("token");
            System.out.print("Inside session validate :");
            System.out.println(token);
            LoginServiceImpl ls=new LoginServiceImpl();
            User u=ls.SessionValidate(token);
            if(u.getEmail().equals(sess.getAttribute("email")) && u.getPassword().equals(sess.getAttribute("password")))
            {
                filterChain.doFilter(servletRequest,servletResponse);
            }
            else
            {
                servletRequest.getRequestDispatcher("index.jsp").forward(servletRequest, servletResponse);
            }
        }
        else
        {
            //RequestDispatcher rd=servletRequest.getRequestDispatcher("index.jsp");
            servletRequest.getRequestDispatcher("index.jsp").forward(servletRequest, servletResponse);
            //rd.forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
