package com.paytm.controller;

import com.paytm.entity.Token;
import com.paytm.entity.User;
import com.paytm.services.LoginServiceImpl;
import com.paytm.services.SignupServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
        System.out.println("hello to index servlet");

        List<String> deptList =signupServiceImpl.listAllDeptByNameService();

        for(String str:deptList)
            System.out.println("hello in UC "+str);

        ModelAndView mv= new ModelAndView();
        mv.setViewName("loginSignup.jsp");
        mv.addObject("deptList",deptList);

        return mv;

    }
//
//    @RequestMapping(value = "/hello",method = RequestMethod.GET)
//    @ResponseBody
//    public List<String> initialPage(HttpServletRequest request, HttpServletResponse response)
//    {
//
//
//        List<String> deptList=signupServiceImpl.listAllDeptByNameService();
//
//        ModelAndView mv =new ModelAndView();
//
//        mv.setViewName("index.jsp");
//        mv.addObject("deptList"  ,deptList);
//
//        for(String d_name:deptList)
//        {
//            System.out.println(d_name +"    In a check loop");
//        }
//
//        System.out.println("hello home page");
//
//    return deptList;
//
//    }

    @RequestMapping(value="/login", method = RequestMethod.POST )
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        request.setAttribute("type", "back");
        System.out.println(request.getAttribute("type"));
        System.out.println("email is :"+request.getParameter("email"));
        try
        {
            boolean flag = ls.UserAuthenticationService(email, password);
            System.out.println("Flag is "+flag+".");
            if (!flag)
            {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
        catch(Exception e)
        {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
//        System.out.println(request.getParameter("password")+"           pass is ");
        System.out.println("Inside Login Controller-----------1");

        //find the user id on the basis of his email
        User u=ls.findUserByEmailService(email);
        //then find if a active session of the user exist
        Token t1=ls.findTokenByUserService(u);
        // if it exists then assign that token to the user otherwise generate a new one
        String modelEmail=u.getEmail();
        if(t1!=null)
        {
            HttpSession session = request.getSession();
            session.setAttribute("email", email);
            //session.setAttribute("password", password);
            session.setAttribute("token", t1.getToken_no());
            session.setAttribute("created", System.currentTimeMillis());
            session.setAttribute("dept", u.getDept());
            System.out.println("Used old token & logged user inside the website...");
            modelEmail= (String) session.getAttribute("email");
        }
        else
        {
            HttpSession session = request.getSession();
            UUID uuid = UUID.randomUUID();
            String randomUUIDString = uuid.toString();

            session.setAttribute("dept", u.getDept());
            session.setAttribute("email", email);
            //session.setAttribute("password", password);
            session.setAttribute("token", randomUUIDString);
            session.setAttribute("created", System.currentTimeMillis());
            //User u = ls.findUserByEmailService(email);
            Token t = new Token();
            //t.setExpiry_time();
            t.setFlag(1);
            t.setToken_no((String) session.getAttribute("token"));
            t.setUser(u);
            t.setCreated(new Date());
            t.setUpdated(new Date());

            System.out.println("Created new token & assigned to the user.");

            ls.createTokenService(t);
            modelEmail= (String) session.getAttribute("email");

            System.out.println("Passed createTokenService.");
       }
        //no caching & force reload
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
        response.setHeader("Expires", "0");

        ModelAndView mv = new ModelAndView();
        mv.setViewName("postLoggedIn.jsp");
        mv.addObject("email",   modelEmail);
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