package com.paytm.services;

<<<<<<< HEAD
import com.paytm.dal.InterestDalImpl;
import com.paytm.entity.Dept;
import com.paytm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class InterestServiceImpl implements InterestService {

    /*@Autowired
    private InterestDalImpl dal;// = new InterestDalImpl();*/

    @Override
    public boolean addInterest(User u, Dept d) {
          Integer u_id =u.getU_id();
          Integer dept_id =d.getDept_id();
          InterestDalImpl dal = new InterestDalImpl();
          return dal.insertInterest(u_id,dept_id);
    }

    @Override
    public boolean removeInterest(User u,Dept d) {
          Integer u_id =u.getU_id();
          Integer dept_id =d.getDept_id();
          InterestDalImpl dal = new InterestDalImpl();
          return dal.deleteInterest(u_id,dept_id);
    }

    @Override
    public List<String> showAllInterest(User u) {
          Integer u_id =u.getU_id();
          InterestDalImpl dal = new InterestDalImpl();
          return dal.showAllInterest(u_id);
=======
import com.paytm.entity.Dept;
import com.paytm.entity.User;

public class InterestServiceImpl implements InterestService {
    @Override
    public void addInterest(User u, Dept d) {

    }

    @Override
    public void removeInterest(Dept d) {

    }

    @Override
    public void showAllInterest(User u) {

>>>>>>> 8cd48fd461a12d87e7dc0ccac7d1fe368b6b21e9
    }

    @Override
    public void showFeed(User u) {

    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 8cd48fd461a12d87e7dc0ccac7d1fe368b6b21e9
