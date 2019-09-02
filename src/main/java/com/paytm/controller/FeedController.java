package com.paytm.controller;

import com.paytm.dal.DeptDalImpl;
import com.paytm.entity.Dept;
import com.paytm.entity.User;
import com.paytm.services.AnswerServiceImpl;
import com.paytm.services.InterestServiceImpl;
import com.paytm.services.QuestionServiceImpl;
import com.paytm.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/*
 * @author: ekansh.gupta
 * @created: 06/08/19
 */

@Controller
public class FeedController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private InterestServiceImpl interestService;

    @Autowired
    private AnswerServiceImpl answerService;

    @Autowired
    private DeptDalImpl deptDal;

    @Autowired
    private QuestionServiceImpl questionService;

    @RequestMapping(value = "/addinterest", method = RequestMethod.POST)
    public ModelAndView addInterest(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(false);

        String deptName=req.getParameter("deptName");
        String email = (String) session.getAttribute("email");

        User u = userService.findUserByEmailService(email);
        if(deptName != null) {
            Dept d = deptDal.findDeptByNameMethod(deptName);
            if(interestService.addInterestService(u,d)){
                req.setAttribute("message","Interest successfully added.");
            }
            else{
                req.setAttribute("message","Interest can't be added.");
            }
        }
        else {
            req.setAttribute("message","You have already added all the Interests.");
        }
        return showAllInterest(req,res);
    }

    @RequestMapping(value = "/removeinterest", method = RequestMethod.POST)
    public ModelAndView removeInterest(HttpServletRequest req, HttpServletResponse res){
        HttpSession session = req.getSession(false);

        String deptName=req.getParameter("deptName");
        String email = (String) session.getAttribute("email");

        User u = userService.findUserByEmailService(email);
        Dept d = deptDal.findDeptByNameMethod(deptName);

        if(u.getDept().getDept_name().equals(d.getDept_name())) {
            req.setAttribute("message","You can't remove your own department.");
        }
        else{
            if(interestService.removeInterestService(u,d)){
                req.setAttribute("message","Interest successfully removed.");
            }
            else{
                req.setAttribute("message","Interest can't be removed.");
            }
        }

        return showAllInterest(req,res);
    }

    @RequestMapping(value = "/profile", method = RequestMethod.POST)
    public ModelAndView  showAllInterest(HttpServletRequest request, HttpServletResponse response)
    {

        System.out.println("inside profile controller");

        HttpSession session = request.getSession(false);
        ModelAndView mv = new ModelAndView();

        String email = (String) session.getAttribute("email");
        User u= userService.findUserByEmailService(email);

        List<String> resultSet = interestService.getUserAllInterestNamesService(u);
        List<Dept> deptSet = deptDal.enterAllAvailableDeptMethod(resultSet);


        mv.setViewName("profile.jsp");
        mv.addObject("listofinterest",resultSet);
        mv.addObject("listofdepartments",deptSet);
        mv.addObject("username",u.getU_name());
        mv.addObject("email",email);

        mv.addObject("myDept",u.getDept().getDept_name());
        request.getAttribute("message");
       // mv.addObject("message","");
        return  mv;
    }
}