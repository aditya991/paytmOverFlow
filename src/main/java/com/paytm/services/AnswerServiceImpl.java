package com.paytm.services;

import com.paytm.dal.AnswerDal;
import com.paytm.entity.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void updateAnswerByAnswerIdService(int id)
    {
        answerDal.updateAnswerByAnswerIdMethod(id);
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
}
