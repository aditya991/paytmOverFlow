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



    public boolean createUserDal(User user)
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


    public boolean validUserEmail(String email)
    {
        try{
            User u=userRepo.findUserByEmail(email);
            System.out.println(u);

            if(u==null)
                return true;

        }
        catch (Exception e) {
        }

        return  false;
    }

    public boolean validUserPhone(String phone)
    {
            try{
                User u=userRepo.findUserByPhone(phone);
                System.out.println(u);

                if(u==null)
                    return true;

              }
             catch (Exception e) {
            }
        return  false;
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
