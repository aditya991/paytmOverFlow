package com.paytm.services;
import com.paytm.dal.InterestDalImpl;
import com.paytm.entity.Dept;
import com.paytm.entity.User;

public class InterestServiceImpl implements InterestService {
    @Override
    public void addInterest(User u, Dept d) {

          Integer u_id =u.getU_id();
          Integer dept_id =d.getDept_id();
          InterestDalImpl dal = new InterestDalImpl();
          dal.insertInterest(u_id,dept_id);
    }

    @Override
    public void removeInterest(User u,Dept d) {
          Integer u_id =u.getU_id();
          Integer dept_id =d.getDept_id();
          InterestDalImpl dal = new InterestDalImpl();
          dal.deleteInterest(u_id,dept_id);


    }

    @Override
    public void removeInterest(Dept d) {

    }

    @Override
    public void showAllInterest(User u) {

    }

    @Override
    public void showFeed(User u) {

    }

}

}
