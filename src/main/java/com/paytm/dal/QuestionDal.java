package com.paytm.dal;
import com.paytm.entity.Dept;
import com.paytm.entity.Question;
import com.paytm.entity.User;

import java.util.List;

public interface QuestionDal {
    void AddQuestionMethod(Question ques  );
    boolean UpdateQuestionMethod(String question,String UpdateQuestion);
    boolean DeleteQuestionMethod(Integer Ques_Id);

    List<Question> showAllQuestionMethod(User user);
}