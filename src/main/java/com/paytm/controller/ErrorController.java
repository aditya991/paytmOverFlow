package com.paytm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {


    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public ModelAndView errorHandling(HttpServletRequest request)
    {

        System.out.println("inside error handler");

        ModelAndView errorPage =new ModelAndView("errorPage.jsp");

        String errorMsg = "";
        int httpErrorCode = (int )request.getAttribute("javax.servlet.error.status_code");


        String errorMesaage="Error Page : Error Code is "+httpErrorCode;


        System.out.println("inside error handler" +httpErrorCode);

        errorPage.addObject("errorMsg", errorMesaage);
        return errorPage;





    }

}
