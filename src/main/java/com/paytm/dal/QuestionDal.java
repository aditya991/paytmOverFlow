package com.paytm.dal;
import com.paytm.entity.Question;
import com.paytm.entity.User;
public interface QuestionDal {
    void AddQuestionMethod(Question ques , User user );
    boolean UpdateQuestionMethod(Integer Ques_Id);
    boolean DeleteQuestionMethod(Integer Ques_Id);
    boolean ValidUserMethod(Integer Ques_Id, User user);
}
