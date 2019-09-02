package com.paytm.dal;
/*
 * @author: aditya10.kumar
 * @created: 09/08/19
 */

import com.paytm.configuration.DBConfiguration;
import com.paytm.entity.Answer;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.repo.AnswerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class AnswerDal
{
    private static final Logger LOG = LoggerFactory.getLogger(DBConfiguration.class);

    @Autowired
    private AnswerRepo answerRepo;

    @Autowired
    private EntityManagerFactory emf;

    public void updateAnswerByAnswerIdMethod(int id, String answer)
    {
        answerRepo.updateAnswerByAnswerId(id, answer);
    }

    public void deleteAnswerByAnswerIdMethod(int id)
    {
        answerRepo.deleteAnswerByAnswerId(id);
    }

    public void saveAnswerMethod(Answer ans)
    {
        EntityManager em=emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(ans);
        em.getTransaction().commit();
        em.close();

        LOG.info("Answer saved....Inside AnswerDal");
    }
    public List<Answer> findAllAnswerByUserMethod(User user)
    {
        return answerRepo.findAllAnswerByUser(user);
    }

    public List<Answer> findAllAnswerByQuestionMethod(Question q)
    {
        LOG.info(("Inside findAllAnswerByQuestionMethod"));
        return answerRepo.findAllAnswerByQuestion(q);
    }
    public void deleteAnswerByQuestionMethod(Question question)
    {
        answerRepo.deleteAnswerByQuestion(question);
    }
}
