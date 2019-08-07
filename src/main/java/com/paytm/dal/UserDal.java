package com.paytm.dal;


import com.paytm.entity.User;
import com.paytm.repo.DeptRepo;
import com.paytm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;


/*
 * @author: aditya10.kumar
 * @created: 07/08/19
 */

import com.paytm.repo.TokenRepo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;



public class UserDal
{

    @Autowired
    private UserRepo userRepo;

    @Autowired

    private DeptRepo deptRepo;

    private TokenRepo tokenRepo;

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


    public User findUserByUserId(int id)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        User u=userRepo.findUserByUserId(id);
        tx.commit();
        em.close();
        return u;
    }
    public int findUserIdByToken(String token)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        int id=tokenRepo.findUserIdByToken(token);
        tx.commit();
        em.close();
        return id;
    }

    public String findPasswordByEmailMethod(String email)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        String password=userRepo.findPasswordByEmail(email);
        tx.commit();
        em.close();
        return password;
    }
}
