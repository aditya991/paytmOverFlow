package com.paytm.dal;
import com.paytm.entity.Question;
import com.paytm.entity.User;
public interface QuestionDal {
    Void AddQuestionDal(Question ques ,User user );
    boolean UpdateQuestionDal(Integer Ques_Id);
    boolean DeleteQuestionDal(Integer Ques_Id);
    boolean ValidUserDal(Integer Ques_Id,User user);
}
