package com.paytm.dal;

import com.paytm.entity.Question;
import com.paytm.entity.User;

public interface QuestionDal
{
    public User getUserByQuestionIdMethod(int id);
    Question getQuestionByQuestionIdMethod(int id);
}
