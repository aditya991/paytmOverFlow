package com.paytm.services;
import com.paytm.entity.Question;
import com.paytm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;


public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private UserRepo userrepo;
    @Autowired
    private EntityManagerFactory emf;


    /**
     * This service takes
     * @param Department
     * @param Question
     * @return
     */
    @Override
    public Integer AddQuestionService(String Department,String Question)
    {  Question ques=new Question();
       ques.setDepartment(Department);
       ques.setQuestion(Question);
      Integer k= ques.getQuestion_Id();

      EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        em.getTransaction().begin();
        em.persist(ques);
        em.getTransaction().commit();
        em.close();

        return k;
    }

    @Override
    public boolean UpdateQuestionService(Integer Ques_Id) {
        return false;
    }

    @Override
    public boolean DeleteQuestionService(Integer Ques_Id) {
        return false;
    }

    @Override
    public boolean ValidUser(Integer Ques_Id) {

        return false;
    }
}