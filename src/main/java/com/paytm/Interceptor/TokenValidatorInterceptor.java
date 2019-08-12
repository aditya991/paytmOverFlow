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

public class TokenValidatorInterceptor implements HandlerInterceptor
{
    @Autowired
    private LoginServiceImpl ls;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String path = ((HttpServletRequest) httpServletRequest).getRequestURI();
        System.out.println(path);
        //see if it works.....

        String email=httpServletRequest.getParameter("email");
        String SEmail= (String) httpServletRequest.getSession().getAttribute("email");
        //if email & session email doesn't exist then there is an
        // intrusion by some else...redirect that person to login page
        if(email==null && SEmail==null)
        {
            httpServletRequest.getRequestDispatcher("index.jsp").forward(httpServletRequest, httpServletResponse);
        }

        String action=httpServletRequest.getParameter("action");
        HttpSession session = httpServletRequest.getSession();
        System.out.println("action is "+action);
        int valid1=ls.isTokenActiveService((String)session.getAttribute("token"));

        System.out.println(valid1);
        if(action !=null && action.equals("login") && valid1==1)
        {
            return true;
        }
            //typecasting needed to access the getSession()method;
            HttpServletRequest request = (HttpServletRequest) httpServletRequest;
            HttpSession sess = request.getSession(false);
            if (sess != null)
            {
                String token = (String) sess.getAttribute("token");
                System.out.print("Inside Token Validator Interceptor :");
                System.out.println(token);

                //query fire
                int valid = ls.isTokenActiveService(token);
                if (valid == 1)
                {
                    int id = ls.findUserIdByTokenService(token);
                    System.out.println("User Id: "+id);
                    User u = ls.findUserByUserIdService(id);

                    //for allowing multiple tabs to one account
                    if(action==null && token!=null)
                    {
                        httpServletRequest.getRequestDispatcher("postLoggedIn.jsp").forward(httpServletRequest, httpServletResponse);
                    }
                    else
                    {
                        return true;
                    }
                    if (u.getEmail().equals(sess.getAttribute("email")) == false)
                    {
                        //login unsuccessful
                        httpServletRequest.getRequestDispatcher("index.jsp").forward(httpServletRequest, httpServletResponse);
                    }
                }
                else
                    httpServletRequest.getRequestDispatcher("index.jsp").forward(httpServletRequest, httpServletResponse);
            }
            System.out.println("Here after redirecting in Interceptor.");
            return true;
        ///}
 }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception
    {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}