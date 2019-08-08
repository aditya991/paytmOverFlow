package com.paytm.services;

import com.paytm.entity.Dept;
import com.paytm.entity.User;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public interface InterestService {

    boolean addInterest(User u, Dept d, EntityManagerFactory emf2);
    boolean removeInterest(User u,Dept d);
    List<String> showAllInterest(User u);
    void showFeed(User u);
}
