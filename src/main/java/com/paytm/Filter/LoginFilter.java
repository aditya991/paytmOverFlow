package com.paytm.Filter;
/*
 * @author: aditya10.kumar
 * @created: 07/08/19
 */

import com.paytm.services.LoginServiceImpl;

import javax.servlet.*;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        String email = servletRequest.getParameter("email");
        String password = servletRequest.getParameter("password");
        LoginServiceImpl ls = new LoginServiceImpl();
        System.out.println("Inside Login Controller");
        try {
            boolean flag = ls.UserAuthenticationService(email, password);
        }
        catch(Exception e)
        {
            servletRequest.getRequestDispatcher("index.jsp").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
