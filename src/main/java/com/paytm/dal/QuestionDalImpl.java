package com.paytm.dal;

import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.repo.QuestionRepo;
import com.paytm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Service
public class QuestionDalImpl implements QuestionDal {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private QuestionRepo questionRepo;
    @Autowired
    private EntityManagerFactory emf;

    @Override
    public void AddQuestionMethod(Question ques, User user )
    {  //ques.setUser(user);
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        em.getTransaction().begin();
        em.persist(ques);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public boolean UpdateQuestionMethod(Integer Ques_Id) {
        return false;
    }

    @Override
    public boolean DeleteQuestionMethod(Integer Ques_Id) {
        return false;
    }

    @Override
    public boolean ValidUserMethod(Integer Ques_Id,User user) {
     //  Question question=questionRepo.getquestion_Idbyquestion(Ques_Id);

     /*  if(user.getU_name().equals(question.g))
           return true;
       else*/
        return false;
    }
}
