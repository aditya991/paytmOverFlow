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

        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource not found";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                break;
            }
        }


        System.out.println("inside error handler" +httpErrorCode);

        errorPage.addObject("errorMsg", errorMsg);
        return errorPage;





    }

}