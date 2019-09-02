package com.paytm.services;

import com.paytm.entity.Question;
import com.paytm.entity.User;

import java.util.List;

public interface QuestionService {
//    Integer AddQuestionService(String Department,String Question );
//    boolean UpdateQuestionService(Integer Ques_Id);
//    boolean DeleteQuestionService(Integer Ques_Id);
    boolean ValidUser(Integer Ques_Id);
    User getUserByQuestionIdService(int id);
    Question getQuestionByQuestionIdService(int id);

    //todo ekansh
    void incrementAnswersCountService(int qid);


    boolean AddQuestionService(String Department, String Question , String email);
    boolean UpdateQuestionService(String question,String UpdateQuestion);
    boolean DeleteQuestionService(String question);

    List<Question> showAllQuestionService(String email);
}
