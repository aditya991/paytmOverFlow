package com.paytm.services;

import com.paytm.entity.Dept;
import com.paytm.entity.User;

import java.util.List;

public interface InterestService {

    boolean addInterest(User u,Dept d);
    boolean removeInterest(User u,Dept d);
    List<String> showAllInterest(User u);
    void showFeed(User u);
}
