package com.paytm.dal;
<<<<<<< HEAD
import com.paytm.entity.Dept;
import com.paytm.entity.Question;
import com.paytm.entity.User;

import java.util.List;

public interface QuestionDal {
    boolean AddQuestionMethod(Question ques  );
    boolean UpdateQuestionMethod(String question,String UpdateQuestion);
    boolean DeleteQuestionMethod(Integer Ques_Id);
    boolean checkExistingQuestionMethod(Question q);
    List<Question> showAllQuestionMethod(User user);
}
=======

import com.paytm.entity.User;

public interface QuestionDal
{
    public User getUserByQuestionIdMethod(int id);
}
>>>>>>> dfb22dd0788400655c45c1c7c01293a985c74ae4
