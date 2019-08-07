package com.paytm.dal;

import com.paytm.entity.Interest;
import com.paytm.repo.InterestRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;

public class InterestDalImpl implements InterestDal {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private InterestRepo interestRepo;

    @Override
    public void insertInterest(Integer u_id, Integer dept_id) {
        Interest i = new Interest(u_id,dept_id);
        i.setCreated(new Date());
        i.setUpdated(new Date());
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        em.getTransaction().begin();
        em.persist(i);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteInterest(Integer u_id, Integer dept_id) {
         Interest i = InterestRepo.getInterestByUIdByDeptId( String.valueOf(u_id),
                                                             String.valueOf(dept_id) );

    }
}
