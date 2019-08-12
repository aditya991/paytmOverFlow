package com.paytm.services;

import com.paytm.entity.Question;
import com.paytm.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface QuestionService {
    void AddQuestionService(String Department, String Question , String email);

    boolean UpdateQuestionService(String Ques,HttpSession session);
    boolean DeleteQuestionService(Integer Ques_Id,HttpSession session);
    boolean ValidUser(String Ques,String email);
    List<String> showAllQuestionService(String email);

}
