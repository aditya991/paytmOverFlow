package com.paytm.dal;


import com.paytm.entity.User;
import com.paytm.repo.DeptRepo;
import com.paytm.repo.TokenRepo;
import com.paytm.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/*
 * @author: aditya10.kumar
 * @created: 07/08/19
 */


import com.paytm.repo.TokenRepo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;


@Component
public class UserDal
{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DeptRepo deptRepo;

    @Autowired
    private TokenRepo tokenRepo;


    @Autowired
    private EntityManagerFactory emf;



    public boolean createUserMethod(User user)
    {



        System.out.println("in user DAL initial        "+ emf);

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();


        em.getTransaction().begin();
        em.persist(user.getDept());
        em.getTransaction().commit();





        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();


        System.out.println("in user DAL final .user must be added to table");


        return true;
    }


    public boolean validUserEmailMethod(String email)
    {
        try{
            User u=userRepo.findUserByEmail(email);
            System.out.println(u);

            if(u==null)
                return false;

        }
        catch (Exception e) {
        }

        return  true;
    }

    public boolean validUserPhoneMethod(String phone)
    {
            try{
                User u=userRepo.findUserByPhone(phone);
                System.out.println(u);

                if(u==null)
                    return false;

              }
             catch (Exception e) {
            }
        return  true;
    }


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


    public User findUserByEmailMethod(String email)
    {

        User u=userRepo.findUserByEmail(email);

        return u;

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
