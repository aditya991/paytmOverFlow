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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.UUID;


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





    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public List<String> initialPage(HttpServletRequest request, HttpServletResponse response)
    {


        List<String> deptList=signupServiceImpl.listAllDeptByNameService();

        ModelAndView mv =new ModelAndView();

        mv.setViewName("index.jsp");
        mv.addObject("deptList"  ,deptList);

        deptList.add("naval");
        deptList.add("naval_k");
        for(String d_name:deptList)
        {
            System.out.println(d_name +"    In a check loop");
        }

        System.out.println("hello home page");


//        request.setAttribute("nk","naval_kishore");

     //   response.setHeader("nk","naval ishore");

    return deptList;

    }

    @RequestMapping(value="/login", method = RequestMethod.POST )
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response)
    {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        System.out.println("Inside Login Controller");

        HttpSession session = request.getSession();
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();

        session.setAttribute("email", email);
        //session.setAttribute("password", password);
        session.setAttribute("token", randomUUIDString);
        session.setAttribute("created", System.currentTimeMillis());

        User u=ls.findUserByEmailService(email);

        Token t= new Token();
        //t.setExpiry_time();
        t.setFlag(1);
        t.setToken_no((String) session.getAttribute("token"));
        t.setUser(u);
        t.setCreated(new Date());
        t.setUpdated(new Date());

        System.out.println("Passed Token.");

        ls.createTokenService(t);

        System.out.println("Passed createTokenService.");

        ModelAndView mv = new ModelAndView();
        mv.setViewName("postLoggedIn.jsp");
        mv.addObject("email", email);
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