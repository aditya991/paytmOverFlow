package com.paytm.controller;

import com.paytm.entity.Token;
import com.paytm.entity.User;
import com.paytm.services.LoginServiceImpl;
import com.paytm.services.SignupServiceImpl;
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

    @Autowired
    private SignupServiceImpl signupServiceImpl ;

    @Autowired
    private LoginServiceImpl ls;

    @RequestMapping(value = "/indexPage" ,method = RequestMethod.GET)
    public  ModelAndView redirectToLogin(HttpServletRequest request,HttpServletResponse response)
    {
        System.out.println("hello to index servlet page");


        HttpSession session =(HttpSession)request.getSession(false);

        List<String> deptList =signupServiceImpl.listAllDeptByNameService();


        ModelAndView mv= new ModelAndView();
        mv.setViewName("loginSignup.jsp");
        mv.addObject("deptList",deptList);


        if(session !=null)
        {

            System.out.println("there is an active session      " + session.getId()+"        "+session.getAttribute("email")+"      "+ session.getAttribute("password") +"      "+ session.getAttribute("token")  );


            mv.addObject("email",session.getAttribute("email"));
            mv.addObject("password",session.getAttribute("password"));
            mv.addObject("token",session.getAttribute("token"));


            mv.setViewName("redirect:login");
        }
        else
        System.out.println("current session is null");



        return mv;

    }



    @RequestMapping(value="/login", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



        ModelAndView mv = new ModelAndView();

        System.out.println("inside login API");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
       
        request.setAttribute("type", "back");
        System.out.println(request.getAttribute("type"));
       
       
        System.out.println("email is :"+request.getParameter("email"));



        // if incorrect details then redirect to index page.
        try
        {
            boolean flag = ls.UserAuthenticationService(email, password);
            System.out.println("Flag is "+flag+".");
            if (flag!=true)
            {
                System.out.println("flag is not true");

                mv.setViewName("index.jsp");
                return mv;
            }
        }
        catch(Exception e)
        {
            System.out.println("flag is not true");

            mv.setViewName("index.jsp");   }
        
        
        System.out.println("Inside Login Controller----------- id passwords are correct");

        //find the user id on the basis of his email
        User user=ls.findUserByEmailService(email);
        //then find if a active session of the user exist
        Token token=ls.findTokenByUserService(user);
        // if it exists then assign that token to the user otherwise generate a new one

        System.out.println("Inside Login Controller----- token is "+token+"  user is  "+user);


        HttpSession session = request.getSession(false);

        if(session == null)           // no existing session create new one
        {
            System.out.println("created new session");
            session = request.getSession(true);
        }
        else {
            System.out.println("already a session");
        }

        //set session attributes
        session.setAttribute("email",email);
        session.setAttribute("password",password);
        session.setAttribute("created", System.currentTimeMillis());



        //Now already a session just check for token
        if(token!=null)   {              // already a valid token
            System.out.println("already an active token ....");
            session.setAttribute("token", token.getToken_no());}
        else                    // create a new token
        {
            System.out.println("creating new token ");

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
            System.out.println("Created new token & assigned to the user.");

            ls.createTokenService(t);
        }

        //no caching
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0");


        mv.setViewName("postLoggedIn.jsp");
        mv.addObject("email",   email);
        return mv;
    }

    @RequestMapping(value="/logout", method = RequestMethod.POST)
    public ModelAndView logout(HttpSession session)
    {
        System.out.println("Logging you out...session Invalidated");
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

        System.out.println("step 1 in controller" +name+"  "+email+"    "+phone);


        SignupServiceImpl signupService =new SignupServiceImpl();


        ModelAndView mv = new ModelAndView();


        System.out.println(" in controller");


        boolean valid_user= signupServiceImpl.checkExistingUserService(email,phone);

        System.out.println(" in controller 1");

        if(valid_user)
        {
            System.out.println("User creating stage");

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

            System.out.println("User already exists ");
            mv.addObject("status", "User already Exists");
        }

        mv.setViewName("index.jsp");
        return mv;
    }
}