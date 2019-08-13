package com.paytm.dal;

import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.repo.QuestionRepo;
import com.paytm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
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
    public boolean AddQuestionMethod(Question ques)
    {
        if(!checkExistingQuestionMethod(ques)) {
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            em.getTransaction().begin();
            em.persist(ques);
            em.getTransaction().commit();
            em.close();
            return true;
        }
        else
           return false;
    }

    @Override
    public boolean checkExistingQuestionMethod(Question q) {
        try{
            Question Q=questionRepo.getQuestionByName(q.getQuestion());
            if(Q==null)
                return false;
        }
        catch (Exception e) {
        }
        return  true;
    }

    @Override
    public boolean UpdateQuestionMethod(String question,String UpdateQuestion)
    {
        questionRepo.updateQuestionByName(UpdateQuestion,question);
        return true;
    }

    @Override
    public boolean DeleteQuestionMethod(Integer Ques_Id)
    {
        questionRepo.deleteQuestionById(Ques_Id);
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