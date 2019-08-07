package com.paytm.dal;

import java.util.List;

public interface InterestDal {

    boolean insertInterest(Integer u_id,Integer dept_id);
    boolean deleteInterest(Integer u_id,Integer dept_id);
    List<String> showAllInterest(Integer u_id);
}
