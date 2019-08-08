package com.paytm.dal;

import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Objects;

public class QuestionDalImpl<UserRepo> implements QuestionService {

    @Autowired
    private UserRepo userrepo;
    @Autowired
    private EntityManagerFactory emf;

    @Override
    public  void AddQuestionDal(Question ques, User user )
    {  ques.setUser(user);
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        em.getTransaction().begin();
        em.persist(ques);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public boolean UpdateQuestionDal(Integer Ques_Id, String Department) {
        return false;
    }

    @Override
    public boolean DeleteQuestionDal(Integer Ques_Id, String Department) {
        return false;
    }



    @Override
    public boolean ValidUser(Integer Ques_Id,User user) {
       String name=getUsernameByQues_Id(Ques_Id);
       if(user.getU_name().equals(name))
           return true;
       else
        return false;
    }
}
