package com.paytm.dal;
/*
 * @author: aditya10.kumar
 * @created: 09/08/19
 */

import com.paytm.entity.Answer;
import com.paytm.repo.AnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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
}
