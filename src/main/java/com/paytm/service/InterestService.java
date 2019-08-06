package com.paytm.service;

import com.paytm.entity.Dept;
import com.paytm.entity.User;

public interface InterestService {

    void addInterest(User u,Dept d);
    void removeInterest(Dept d);
    void showAllInterest(User u);
    void showFeed(User u);
}
