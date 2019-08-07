package com.paytm.services;

import com.paytm.entity.Dept;
import com.paytm.entity.User;

<<<<<<< HEAD
import java.util.List;

public interface InterestService {

    boolean addInterest(User u,Dept d);
    boolean removeInterest(User u,Dept d);
    List<String> showAllInterest(User u);
=======
public interface InterestService {

    void addInterest(User u, Dept d);
    void removeInterest(Dept d);
    void showAllInterest(User u);
>>>>>>> 8cd48fd461a12d87e7dc0ccac7d1fe368b6b21e9
    void showFeed(User u);
}
