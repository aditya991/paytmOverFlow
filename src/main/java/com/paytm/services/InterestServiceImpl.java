package com.paytm.services;

import com.paytm.dal.InterestDalImpl;
import com.paytm.entity.Dept;
import com.paytm.entity.Interest;
import com.paytm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class InterestServiceImpl implements InterestService {

    /*@Autowired
    private InterestDalImpl dal;// = new InterestDalImpl();*/

    public InterestServiceImpl() {}


    @Override
    public boolean addInterest(User u, Dept d) {
        Integer u_id = u.getU_id();
        Integer dept_id = d.getDept_id();
        InterestDalImpl dal = new InterestDalImpl();
        System.out.println("Inside addInterest");
        return dal.insertInterest(u_id, dept_id);
    }

    @Override
    public boolean removeInterest(User u, Dept d) {
        Integer u_id = u.getU_id();
        Integer dept_id = d.getDept_id();
        InterestDalImpl dal = new InterestDalImpl();
        return dal.deleteInterest(u_id, dept_id);
    }

    @Override
    public List<String> showAllInterest(User u) {
        Integer u_id = u.getU_id();
        InterestDalImpl dal = new InterestDalImpl();
        return dal.showAllInterest(u_id);
    }

    @Override
    public void showFeed(User u) {

    }
}