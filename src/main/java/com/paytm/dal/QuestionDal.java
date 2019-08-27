package com.paytm.dal;

import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.entity.Dept;
import com.paytm.entity.Question;
import com.paytm.entity.User;

import java.util.List;

public interface QuestionDal
{
    public User getUserByQuestionIdMethod(int id);
    Question getQuestionByQuestionIdMethod(int id);
    boolean AddQuestionMethod(Question ques  );
    boolean UpdateQuestionMethod(String question,String UpdateQuestion);
    boolean DeleteQuestionMethod(Integer Ques_Id);
    boolean checkExistingQuestionMethod(Question q);
    List<Question> showAllQuestionMethod(User user);
}
