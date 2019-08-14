package com.paytm.dal;

import com.paytm.entity.User;
import com.paytm.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuestionDalImpl implements QuestionDal
{

    @Autowired
    private QuestionRepo questionRepo;

    @Override
    public User getUserByQuestionIdMethod(int id)
    {
        return questionRepo.getUserByQuestionId(id);
    }
}
