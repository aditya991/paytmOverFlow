package com.paytm.dal;

import com.paytm.entity.Dept;
import com.paytm.entity.User;

public interface InterestDal {

    public void insertInterest(Integer u_id, Integer dept_id);
    public void removeInterest(User u, Dept d);
}
