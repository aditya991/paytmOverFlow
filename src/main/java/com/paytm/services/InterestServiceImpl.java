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
import java.util.List;

@Service
public class InterestServiceImpl implements InterestService {

//    @Autowired
//    private InterestDalImpl interestDal;
    /*@Autowired
    private EntityManagerFactory emf2;*/

    @Override
    public boolean addInterest(User u, Dept d,EntityManagerFactory emf2) {
        Integer u_id = u.getU_id();
        Integer dept_id = d.getDept_id();
        System.out.print(u_id);
        System.out.print(dept_id);
      //  InterestDalImpl dal = new InterestDalImpl();
        System.out.println("Inside addInterest");

        Interest i = new Interest(u_id, dept_id);
        System.out.println(i);
//            i.setCreated(new Date());
//            i.setUpdated(new Date());
           System.out.println(emf2);
           EntityManager em = emf2.createEntityManager();
           EntityTransaction tx = em.getTransaction();
           em.getTransaction().begin();
           em.persist(i);
            em.getTransaction().commit();
            em.close();
        /*boolean x=interestDal.insertInterestMethod(u_id, dept_id);

        System.out.print(x);*/
        return true;
    }

    @Override
    public boolean removeInterest(User u, Dept d) {
        Integer u_id = u.getU_id();
        Integer dept_id = d.getDept_id();
        InterestDalImpl dal = new InterestDalImpl();    ////////////////////////////////////////
        return dal.deleteInterestMethod(u_id, dept_id);
    }

    @Override
    public List<String> showAllInterest(User u) {
        Integer u_id = u.getU_id();
        InterestDalImpl dal = new InterestDalImpl();   //////////////////////////////////////
        return dal.showAllInterestMethod(u_id);
    }

    @Override
    public void showFeed(User u) {

    }
}