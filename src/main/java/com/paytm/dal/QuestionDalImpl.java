package com.paytm.dal;

import com.paytm.entity.Dept;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.repo.QuestionRepo;
import com.paytm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Iterator;
import java.util.List;

@Component
public class QuestionDalImpl implements QuestionDal {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private QuestionRepo questionRepo;


    @Autowired
    private EntityManagerFactory emf;

    @Override
    public void AddQuestionMethod(Question ques )
    {
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        em.getTransaction().begin();
        em.persist(ques);
        em.getTransaction().commit();
        em.close();

    }

    @Override
    public boolean UpdateQuestionMethod(String question,String UpdateQuestion)
    {
        questionRepo.removeQuestionByQuestion(UpdateQuestion,question);
        return true;
    }

    @Override
    public boolean DeleteQuestionMethod(Integer Ques_Id)
    {
        questionRepo.deleteByQuestion_Id(Ques_Id);
        return false;
    }



    @Override
    public List<Question> showAllQuestionMethod(User user) {
        System.out.println("inside showAll");

        return questionRepo.getQuestionByUser(user);

        //  List<Question> QuestionList = r




        // return QuestionList;
    }
}