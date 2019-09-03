package com.paytm.dal;

import com.paytm.configuration.DBConfiguration;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.repo.QuestionRepo;
import com.paytm.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Component
public class QuestionDalImpl implements QuestionDal {

    private static final Logger LOG = LoggerFactory.getLogger(DBConfiguration.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private QuestionRepo questionRepo;


    @Autowired
    private EntityManagerFactory emf;

    @Override
    public User getUserByQuestionIdMethod(int id)
    {
        return questionRepo.getUserByQuestionId(id);
    }

    @Override
    public Question getQuestionByQuestionIdMethod(int id)
    {
        return questionRepo.getQuestionByQuestionId(id);
    }

    @Override
    public boolean AddQuestionMethod(Question ques)
    {
        if(!checkExistingQuestionMethod(ques)) {
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            em.persist(ques);
            tx.commit();
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
    public boolean DeleteQuestionMethod(int ques_id)
    {
//        System.out.println(ques_id);
        questionRepo.deleteQuestionById(ques_id);
        return true;
    }

    @Override
    public List<Question> showAllQuestionMethod(User user) {
        LOG.info("inside showAll");

        return questionRepo.getQuestionByUser(user);

        //  List<Question> QuestionList = r
        // return QuestionList;
    }
}
