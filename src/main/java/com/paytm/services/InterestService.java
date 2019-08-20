package com.paytm.services;

import com.paytm.entity.Dept;
import com.paytm.entity.Interest;
import com.paytm.entity.User;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public interface InterestService {

    boolean addInterestService(User u, Dept d);//, EntityManagerFactory emf2);
    boolean removeInterestService(User u, Dept d);
    List<String> getUserAllInterestNamesService(User u);
    List<Dept> getUserAllInterestService(User u);

        void showFeedService(User u);
}
