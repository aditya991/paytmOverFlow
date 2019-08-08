package com.paytm.services;

import com.paytm.entity.Dept;
import com.paytm.entity.User;

public interface InterestService {

<<<<<<< HEAD
    void addInterest(User u,Dept d);
    void removeInterest(User u,Dept d);
=======
    void addInterest(User u, Dept d);
    void removeInterest(Dept d);
>>>>>>> 653cab14fe340ecc435755bbe96bbc97f1397f2c
    void showAllInterest(User u);
    void showFeed(User u);
}
