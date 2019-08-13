package com.paytm.services;

import com.paytm.entity.Dept;
import com.paytm.entity.Question;
import com.paytm.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface QuestionService {
    void AddQuestionService(String Department, String Question , String email);

    boolean UpdateQuestionService(String question,String UpdateQuestion);
    boolean DeleteQuestionService(String question);

    List<Question> showAllQuestionService(String email);

}