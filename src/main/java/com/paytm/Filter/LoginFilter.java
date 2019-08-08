package com.paytm.Filter;
/*
 * @author: aditya10.kumar
 * @created: 07/08/19
 */
import com.paytm.dal.UserDal;
import com.paytm.services.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import java.io.IOException;

@Component
public class LoginFilter implements Filter {

    @Autowired
    private LoginServiceImpl ls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        String email = servletRequest.getParameter("email");
        String password = servletRequest.getParameter("password");

        System.out.println("Inside Login Filter");
        try
        {
            boolean flag = ls.UserAuthenticationService(email, password);
            System.out.println("Flag is "+flag+". That email doesn't exist.");
            if (flag)
            {
                filterChain.doFilter(servletRequest, servletResponse);
            }
            else
            {
                servletRequest.getRequestDispatcher("index.jsp").forward(servletRequest, servletResponse);
            }
        }
        catch(Exception e)
        {
            servletRequest.getRequestDispatcher("index.jsp").forward(servletRequest, servletResponse);
        }
    }
    @Override
    public void destroy() {}
}
