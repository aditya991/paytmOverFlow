package com.paytm.controller;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;


@Controller
public class UserController {










    @RequestMapping(value = "/signup" ,method = RequestMethod.POST )
    public ModelAndView signup(HttpServletRequest request, HttpServletResponse response)
    {

        String name= request.getParameter("name");
        String email= request.getParameter("email");


    }
}
