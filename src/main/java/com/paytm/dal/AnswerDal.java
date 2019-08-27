package com.paytm.dal;
/*
 * @author: aditya10.kumar
 * @created: 09/08/19
 */

import com.paytm.entity.Answer;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.repo.AnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class AnswerDal
{
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

        System.out.println("Answer saved....Inside AnswerDal");
    }
    public List<Answer> findAllAnswerByUserMethod(User user)
    {
        return answerRepo.findAllAnswerByUser(user);
    }

    public List<Answer> findAllAnswerByQuestionMethod(Question q)
    {
        System.out.println(("Inside findAllAnswerByQuestionMethod"));
        return answerRepo.findAllAnswerByQuestion(q);
    }
}
