package com.paytm.controller;

import com.paytm.configuration.DBConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {

    private static final Logger LOG = LoggerFactory.getLogger(DBConfiguration.class);
    @RequestMapping(value = "errors", method = RequestMethod.GET)
    public ModelAndView errorHandling(HttpServletRequest request)
    {

        LOG.info("inside error handler");

        ModelAndView errorPage =new ModelAndView("errorPage.jsp");

        String errorMsg = "";
        int httpErrorCode = (int )request.getAttribute("javax.servlet.error.status_code");


        String errorMesaage="Error Page : Error Code is "+httpErrorCode;


        LOG.info("inside error handler" +httpErrorCode);

        errorPage.addObject("errorMsg", errorMesaage);
        return errorPage;





    }

}
