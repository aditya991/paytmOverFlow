package com.paytm.dal;

<<<<<<< HEAD
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
=======
import com.paytm.entity.User;
import com.paytm.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionDalImpl implements QuestionDal
{
>>>>>>> dfb22dd0788400655c45c1c7c01293a985c74ae4

    @Autowired
    private QuestionRepo questionRepo;

<<<<<<< HEAD
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

    //todo ekansh
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
=======
    @Override
    public User getUserByQuestionIdMethod(int id)
    {
        return questionRepo.getUserByQuestionId(id);
    }
}
>>>>>>> dfb22dd0788400655c45c1c7c01293a985c74ae4
