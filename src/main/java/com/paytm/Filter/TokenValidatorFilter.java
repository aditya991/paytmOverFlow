package com.paytm.Filter;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
import com.paytm.entity.User;
import com.paytm.services.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component("ValidatorFilter")
public class TokenValidatorFilter implements Filter {

    @Autowired
    private LoginServiceImpl ls;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
    {
        String path = ((HttpServletRequest) servletRequest).getRequestURI();
        System.out.println(path);
        if (path.startsWith("/paytmOverFlow_war_exploded/login") || path.startsWith("/paytmOverFlow_war_exploded/signup"))
        {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        else
        {
            //typecasting needed to access the getSession()method;
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpSession sess = request.getSession(false);
            if (sess != null)
            {
                String token = (String) sess.getAttribute("token");
                System.out.print("Inside session validate :");
                System.out.println(token);

                //query fire
                int id = ls.findUserIdByTokenService(token);
                System.out.println(id);

                //query fire
                User u= ls.findUserByUserIdService(id);


                if (u.getEmail().equals(sess.getAttribute("email")))
                {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
                else
                {
                    //login unsuccessful
                    servletRequest.getRequestDispatcher("index.jsp").forward(servletRequest, servletResponse);
                }
            }
            else
            {
                //login unsuccessful
                //RequestDispatcher rd=servletRequest.getRequestDispatcher("index.jsp");
                servletRequest.getRequestDispatcher("index.jsp").forward(servletRequest, servletResponse);
                //rd.forward(servletRequest, servletResponse);
            }
        }
    }
    @Override
    public void destroy() {}
}
