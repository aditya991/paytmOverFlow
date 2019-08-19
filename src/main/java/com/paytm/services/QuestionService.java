package com.paytm.services;

import com.paytm.entity.Dept;
import com.paytm.entity.Question;
import com.paytm.entity.User;
<<<<<<< HEAD

import javax.servlet.http.HttpSession;
import java.util.List;

public interface QuestionService {
    boolean AddQuestionService(String Department, String Question , String email);

    boolean UpdateQuestionService(String question,String UpdateQuestion);
    boolean DeleteQuestionService(String question);

    List<Question> showAllQuestionService(String email);

}
=======

public interface QuestionService {
    Integer AddQuestionService(String Department,String Question );
    boolean UpdateQuestionService(Integer Ques_Id);
    boolean DeleteQuestionService(Integer Ques_Id);
    boolean ValidUser(Integer Ques_Id);
    User getUserByQuestionIdService(int id);
}
>>>>>>> dfb22dd0788400655c45c1c7c01293a985c74ae4
