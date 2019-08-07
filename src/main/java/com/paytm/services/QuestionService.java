package com.paytm.services;

import com.paytm.entity.Question;

public interface QuestionService {
    Question AddQuestion(String Ques, Integer Ques_Id);
    boolean UpdateQuestion(Integer Ques_Id,String Department);
    boolean DeleteQuestion(Integer Ques_Id,String Department);

}
