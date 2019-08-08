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
    public  void AddQuestionMethod(Question ques, User user )
    {  ques.setUser(user);
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        em.getTransaction().begin();
        em.persist(ques);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public boolean UpdateQuestionMethod(Integer Ques_Id, String Department) {
        return false;
    }

    @Override
    public boolean DeleteQuestionMethod(Integer Ques_Id, String Department) {
        return false;
    }



    @Override
    public boolean ValidUserMethod(Integer Ques_Id,User user) {
       Question question=getUsernameByquestion_Id(Ques_Id);

     /*  if(user.getU_name().equals(question.g))
           return true;
       else*/
        return false;
    }
}
