package com.paytm.services;

import com.paytm.dal.InterestDalImpl;
import com.paytm.entity.Dept;
import com.paytm.entity.Interest;
import com.paytm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

@Service
public class InterestServiceImpl implements InterestService {

    @Autowired
    private InterestDalImpl interestDal;

    @Autowired
    private EntityManagerFactory EMF;

    @Override
    //public boolean addInterestService(User u, Dept d, EntityManagerFactory emf2) {
    public boolean addInterestService(User u, Dept d) {
        Integer u_id = u.getU_id();
        Integer dept_id = d.getDept_id();

        System.out.println("Inside addInterest");

        Interest i = new Interest(u_id, dept_id);
        i.setCreated(new Date());
        i.setUpdated(new Date());

        //  EMF=emf2;
        EntityManager em = EMF.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        em.getTransaction().begin();
        em.persist(i);
        em.getTransaction().commit();
        em.close();

        System.out.println("Interest Added.");
        return true;
    }

    @Override
    public boolean removeInterestService(User u, Dept d) {
        Integer u_id = u.getU_id();
        Integer dept_id = d.getDept_id();
        return interestDal.deleteInterestMethod(u_id, dept_id);
    }

    @Override
    public List<String> showAllInterestService(User u) {
        Integer u_id = u.getU_id();

        System.out.println("in showAllInterestService      "+u_id);

        List<String> l= interestDal.showAllInterestMethod(u_id);

        for(String str:l)
            System.out.println("12"+str);


        return l;

    }

    @Override
    public void showFeedService(User u) {

    }
}