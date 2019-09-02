package com.paytm.controller;

import com.google.common.hash.Hashing;
import com.paytm.configuration.DBConfiguration;
import com.paytm.entity.Token;
import com.paytm.entity.User;
import com.paytm.services.LoginServiceImpl;
import com.paytm.services.SignupServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.nio.charset.StandardCharsets;
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
    private static final Logger LOG = LoggerFactory.getLogger(DBConfiguration.class);

    @Autowired
    private SignupServiceImpl signupServiceImpl ;

    @Autowired
    private LoginServiceImpl ls;



    @RequestMapping(value = "/indexPage" ,method = RequestMethod.GET)
    public  ModelAndView redirectToLogin(HttpServletRequest request,HttpServletResponse response)
    {
        LOG.info("hello to index servlet page");


        HttpSession session =(HttpSession)request.getSession(false);

        List<String> deptList =signupServiceImpl.listAllDeptByNameService();


        ModelAndView mv= new ModelAndView();
        mv.setViewName("loginSignup.jsp");
        mv.addObject("deptList",deptList);

        if(session !=null)
        {
            LOG.info("there is an active session      " + session.getId()+"        "+session.getAttribute("email")+"      "+ session.getAttribute("password") +"      "+ session.getAttribute("token")  );
            mv.addObject("email",session.getAttribute("email"));
            mv.addObject("password",session.getAttribute("password"));
            mv.addObject("token",session.getAttribute("token"));
            mv.setViewName("redirect:login");
        }
        else
            LOG.info("current session is null");
        return mv;

    }
    @RequestMapping(value="/login", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ModelAndView mv = new ModelAndView();

        LOG.info("inside login API");

        String email = request.getParameter("email");
        String password = request.getParameter("password");

//        password= Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        request.setAttribute("type", "back");
//        LOG.info(request.getAttribute("type"));
        LOG.info("email is :"+request.getParameter("email"));

        // if incorrect details then redirect to index page.
        try
        {
            boolean flag = ls.UserAuthenticationService(email, password);
            LOG.info("Flag is "+flag+".");
            if (flag!=true)
            {
                LOG.info("flag is not true");

                mv.setViewName("index.jsp");
                return mv;
            }
        }
        catch(Exception e)
        {
            LOG.info("flag is not true");

            mv.setViewName("index.jsp");   }
        
        
        LOG.info("Inside Login Controller----------- id passwords are correct");

        //find the user id on the basis of his email
        User user=ls.findUserByEmailService(email);
        //then find if a active session of the user exist
        Token token=ls.findTokenByUserService(user);
        // if it exists then assign that token to the user otherwise generate a new one

        LOG.info("Inside Login Controller----- token is "+token+"  user is  "+user);


        HttpSession session = request.getSession(false);

        if(session == null)           // no existing session create new one
        {
            LOG.info("created new session");
            session = request.getSession(true);
        }
        else {
            LOG.info("already a session");
        }

        //set session attributes
        session.setAttribute("email",email);
        session.setAttribute("password",password);
        session.setAttribute("created", System.currentTimeMillis());



        //Now already a session just check for token
        if(token!=null)   {              // already a valid token
            LOG.info("already an active token ....");
            session.setAttribute("token", token.getToken_no());}
        else                    // create a new token
        {
            LOG.info("creating new token ");

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
            LOG.info("Created new token & assigned to the user.");

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
        LOG.info("Logging you out...session Invalidated");
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
//        password=Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();

        LOG.info("step 1 in controller" +name+"  "+email+"    "+phone);


        SignupServiceImpl signupService =new SignupServiceImpl();


        ModelAndView mv = new ModelAndView();


        LOG.info(" in controller");


        boolean valid_user= signupServiceImpl.checkExistingUserService(email,phone);

        LOG.info(" in controller 1");

        if(valid_user)
        {
            LOG.info("User creating stage");

            boolean created=signupServiceImpl.createUserService(name,email,phone,password,dept);

            if(created) {
                mv.addObject("status", "User Created successfully");
            }
            else {
                mv.addObject("status","Error in creating user");
            }
        }
        else
        {

            LOG.info("User already exists ");
            mv.addObject("status", "User already Exists");
        }



        mv.setViewName("index.jsp");
        return mv;
    }


}