package com.paytm.dal;
/*
 * @author: aditya10.kumar
 * @created: 07/08/19
 */

import com.paytm.entity.Token;
import com.paytm.entity.User;
import com.paytm.repo.TokenRepo;
import com.paytm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;

@Controller
public class UserDal
{
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TokenRepo tokenRepo;

    @Autowired
    private EntityManagerFactory emf;

//    public void saveuser()
//    {
//        User u= new User();
//        u.getPassword("");
//        u.getEmail();
//    }

    public User findUserByUserIdMethod(int id)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        User u=userRepo.findUserByUserId(id);
        tx.commit();
        em.close();
        return u;
    }
    public int findUserIdByTokenMethod(String token)
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
    public User findUserByEmailMethod(String email)
    {
        User u= userRepo.findUserByEmail(email);
        return u;
    }

    public void createTokenMethod(Token tok)
    {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(tok);
        tx.commit();
        em.close();
    }
}
