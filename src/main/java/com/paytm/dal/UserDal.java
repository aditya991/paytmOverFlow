package com.paytm.dal;

import com.paytm.entity.Token;
import com.paytm.entity.User;
import com.paytm.repo.DeptRepo;
import com.paytm.repo.TokenRepo;
import com.paytm.repo.UserRepo;
import com.paytm.services.InterestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/*
 * @author: aditya10.kumar
 * @created: 07/08/19
 */

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
    private InterestServiceImpl interestService;

    @Autowired
    private EntityManagerFactory emf;

    public boolean createUserMethod(User user) {
        System.out.println("in user DAL initial "+ emf);

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();

        //ekansh
        boolean isAdded = interestService.addInterestService(user, user.getDept());//,emf);
        System.out.println("in user DAL final .user must be added to table");
        return true;
    }

    public boolean validUserEmailMethod(String email) {
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

    public boolean validUserPhoneMethod(String phone) {
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

    public User findUserByUserIdMethod(int id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        User u=userRepo.findUserByUserId(id);
        tx.commit();
        em.close();
        return u;
    }

    public int findUserIdByTokenMethod(String token) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        int id;
        try
        {
            User u = tokenRepo.findUserIdByToken(token);
            id=u.getU_id();
        }
        catch(Exception e)
        {
            id=0;
        }

        tx.commit();
        em.close();
        return id;
    }

    public String findPasswordByEmailMethod(String email) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        String password=userRepo.findPasswordByEmail(email);
        tx.commit();
        em.close();
        return password;
    }

    public User findUserByEmailMethod(String email) {
        System.out.println("Inside findUserByEmailMethod");
        User u= userRepo.findUserByEmail(email);
        System.out.println("in find"+ u);
        return u;
    }

    public void createTokenMethod(Token tok) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(tok);
        tx.commit();
        em.close();
    }

    public boolean isTokenActiveMethod(String token)
    {
        try {
            boolean flag = tokenRepo.isSessionActive(token);
            return flag;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public void markSessionInactivemethod(String token)
    {
        tokenRepo.markSessionInactive(token);
    }
    public Token findTokenByUserMethod(User user)
    {
        try
        {
            return tokenRepo.findTokenByUser(user);
        }
        catch(Exception e)
        {
            return null;
        }
    }
}