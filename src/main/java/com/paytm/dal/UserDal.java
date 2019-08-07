package com.paytm.dal;
/*
 * @author: aditya10.kumar
 * @created: 07/08/19
 */

import com.paytm.entity.User;
import com.paytm.repo.TokenRepo;
import com.paytm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class UserDal
{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private EntityManagerFactory emf;

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
