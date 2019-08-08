package com.paytm.services;

import com.paytm.entity.Question;

import javax.servlet.http.HttpSession;

public interface QuestionService {
    void AddQuestionService(String Department, String Question , HttpSession session);
    boolean UpdateQuestionService(Integer Ques_Id,HttpSession session);
    boolean DeleteQuestionService(Integer Ques_Id,HttpSession session);
    boolean ValidUser(Integer Ques_Id,HttpSession session);

}
