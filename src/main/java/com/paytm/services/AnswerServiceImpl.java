package com.paytm.services;

import com.paytm.dal.AnswerDal;
import com.paytm.entity.Answer;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @author: aditya10.kumar
 * @created: 09/08/19
 */
@Service
public class AnswerServiceImpl implements AnswerService
{
    @Autowired
    private AnswerDal answerDal;

    @Override
    public void updateAnswerByAnswerIdService(int id, String answer)
    {
        answerDal.updateAnswerByAnswerIdMethod(id, answer);
    }
    public void deleteAnswerByAnswerIdService(int id)
    {
        answerDal.deleteAnswerByAnswerIdMethod(id);
    }

    @Override
    public void saveAnswerService(Answer ans)
    {
        answerDal.saveAnswerMethod(ans);
    }

    @Override
    public List<Answer> findAllAnswerByUserService(User user)
    {
        return answerDal.findAllAnswerByUserMethod(user);
    }

    @Override
    public List<Answer> findAllAnswerByQuestionService(Question q)
    {
        return answerDal.findAllAnswerByQuestionMethod(q);
    }
}
