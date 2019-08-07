package com.paytm.dal;

import com.paytm.entity.User;
import com.paytm.repo.DeptRepo;
import com.paytm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class UserDal {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DeptRepo deptRepo;

    @Autowired
    private EntityManagerFactory emf;

    private EntityManager em = emf.createEntityManager();

    public UserDal(){
    }

    public boolean createUserDal(User user)
    {

//        EntityTransaction tx = em.getTransaction();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();

        return true;
    }


    public boolean validUserEmail(String email)
    {
        return true;
    }

    public boolean validUserPhone(String phone)
    {
        return true;
    }

}
