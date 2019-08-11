package com.paytm.services;

import com.paytm.entity.Question;

public interface QuestionService {
    Integer AddQuestionService(String Department,String Question );
    boolean UpdateQuestionService(Integer Ques_Id);
    boolean DeleteQuestionService(Integer Ques_Id);
    boolean ValidUser(Integer Ques_Id);

}
