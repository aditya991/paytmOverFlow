package com.paytm.controller;

import com.paytm.entity.Token;
import com.paytm.entity.User;
import com.paytm.services.LoginServiceImpl;
import com.paytm.services.SignupServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */

@Controller
public class UserController
{
    private static final Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    private SignupServiceImpl signupServiceImpl ;

    @Autowired
    private LoginServiceImpl ls;

    @RequestMapping(value = "/indexPage" ,method = RequestMethod.GET)
    public  ModelAndView redirectToLogin(HttpServletRequest request,HttpServletResponse response)
    {
        logger.debug("Inside redirectToLogin Method");

        HttpSession session =(HttpSession)request.getSession(false);
        List<String> deptList =signupServiceImpl.listAllDeptByNameService();

        ModelAndView mv= new ModelAndView();
        mv.setViewName("loginSignup.jsp");
        mv.addObject("deptList",deptList);


        if(session !=null)
        {
            logger.debug("There is an active session " + session.getId() + " ");
            logger.debug(session.getAttribute("email") + " " + session.getAttribute("password"));
            logger.debug(" " + session.getAttribute("token"));

            mv.addObject("email",session.getAttribute("email"));
            mv.addObject("password",session.getAttribute("password"));
            mv.addObject("token",session.getAttribute("token"));

            mv.setViewName("redirect:login");
        }
        else
        logger.debug("Current session is null");

        return mv;

    }

    @RequestMapping(value="/login", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.debug("Inside login API");

        ModelAndView mv = new ModelAndView();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        request.setAttribute("type", "back");

        logger.debug("Type of request : " + request.getAttribute("type"));
        logger.debug("Email entered by user is : " + request.getParameter("email"));

        // if incorrect details then redirect to index page.
        try {
            boolean flag = ls.UserAuthenticationService(email, password);
            logger.debug("flag is " + flag + ".");
            if (!flag) {
                logger.debug("Flag is not true");
                mv.setViewName("index.jsp");
                return mv;
            }
        }
        catch(Exception e) {
            logger.error("flag is not true");
            mv.setViewName("index.jsp");
        }
        
        logger.debug("Inside Login API : Id passwords are correct");

        //find the user id on the basis of his email
        User user=ls.findUserByEmailService(email);
        //then find if a active session of the user exist
        Token token=ls.findTokenByUserService(user);
        // if it exists then assign that token to the user otherwise generate a new one

        logger.debug("Inside Login API : token is " + token + " user is " + user);

        HttpSession session = request.getSession(false);

        if(session == null)           // no existing session create new one
        {
            logger.debug("Created new session.");
            session = request.getSession(true);
        }
        else {
            logger.debug("Already a Session.");
        }

        //set session attributes
        session.setAttribute("email",email);
        session.setAttribute("password",password);
        session.setAttribute("created", System.currentTimeMillis());

        //Now already a session just check for token
        if(token!=null) {
            // already a valid token
            logger.debug("Already an active token.");
            session.setAttribute("token", token.getToken_no());
        } else {
            // create a new token
            logger.debug("Creating new token.");

            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString();
            session.setAttribute("token", randomUUIDString);

            Token t = new Token();
            //t.setExpiry_time();
            t.setFlag(true);
            t.setToken_no((String) session.getAttribute("token"));
            t.setUser(user);
            t.setCreated(new Date());
            t.setUpdated(new Date());

            logger.debug("Created new token & assigned to the user.");

            ls.createTokenService(t);
        }

        //no caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0");

        mv.setViewName("userFeedHome.jsp");
        mv.addObject("email",   email);
        return mv;
    }

    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public ModelAndView logout(HttpSession session)
    {
        logger.info("Logging you out...session Invalidated");
        ModelAndView mv = new ModelAndView("index.jsp");

        ls.markSessionInactiveService((String)session.getAttribute("token"));   //mark that session id inactive

        session.invalidate();

        return mv;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ModelAndView signup(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String dept = request.getParameter("dept");

        logger.debug("Step 1 in signup controller " + name + " " + email + "  " + phone);

        SignupServiceImpl signupService =new SignupServiceImpl();
        ModelAndView mv = new ModelAndView();

        boolean valid_user= signupServiceImpl.checkExistingUserService(email,phone);

        if(valid_user)
        {
            logger.debug("User creating stage");

            boolean created=signupServiceImpl.createUserService(name,email,phone,password,dept);

            if(created) {
                logger.debug("User Created successfully");
                mv.addObject("status", "User Created successfully");
            }
            else {
                logger.error("Error in creating User");
                mv.addObject("status","Error in creating user");
            }
        }
        else
        {
            logger.debug("User already exists ");
            mv.addObject("status", "User already Exists");
        }

        mv.setViewName("index.jsp");
        return mv;
    }
}