package com.paytm.Interceptor;
/*
 * @author: aditya10.kumar
 * @created: 08/08/19
 */

import com.paytm.configuration.DBConfiguration;
import com.paytm.entity.Token;
import com.paytm.entity.User;
import com.paytm.services.LoginServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TokenValidatorInterceptor implements HandlerInterceptor
{

    private static final Logger LOG = LoggerFactory.getLogger(DBConfiguration.class);

    @Autowired
    private LoginServiceImpl ls;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {


        String path = ((HttpServletRequest) request).getRequestURI();
        LOG.info("Request path is ....." + path);

        HttpSession session =(HttpSession) request.getSession(false);
        String requestEmail=(String) request.getParameter("email");


        LOG.info("inside interceptor . Current session is ---> " + session);



        //user may be a fresh user or existing user who logged in before and not logged out(closed window.).
        if(session == null)
            {

                LOG.info("session is null");

                //come here from other then login page then redirect to login

                try {
                    if (!request.getParameter("action").equals("login")) {
                        LOG.info("system is here");
                        response.sendRedirect(request.getContextPath() + "/index.jsp");
                        return false;
                    }
                }
                catch (Exception e)
                {
                    response.sendRedirect(request.getContextPath() + "/index.jsp");
                    return false;
                }


                LOG.info("session is null ----->< inside validator interceptor        +email in request is "+requestEmail);

                User user= ls.findUserByEmailService(requestEmail);

                LOG.info("interceptor "+user);


                Token t=ls.findTokenByUserService(user);

                LOG.info("interceptor "+t);

                LOG.info("return from interceptor (null session )");
                return true;

            }
        else            //have an active session (with valid token or with invalid token )
            {
               String sessionEmail =(String) session.getAttribute("email") ;
               String sessionToken= (String) session.getAttribute("token");
             //  String action=session.getAttribute("action");

               boolean validToken =ls.isTokenActiveService(sessionToken);


               //logged in user             have an active token            just validate user email with token
                if(validToken==true) {

                int id = ls.findUserIdByTokenService(sessionToken);
                LOG.info("User Id: " + id);
                User u = ls.findUserByUserIdService(id);

                    if(u.getEmail().equals(sessionEmail)) {             // comparing token and session email.....
                            return true;
                    }
                    else {
                            ls.markSessionInactiveService(sessionToken);    // invalidate token
                            session.invalidate();
                            response.sendRedirect(request.getContextPath()+ "/index.jsp");
                            return  false;
                    }


                }
                else        // new user       create a token for him
                {
                    session.invalidate();
                    response.sendRedirect(request.getContextPath()+ "/index.jsp");
                    return false;
                }
            }
 }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}