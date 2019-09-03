package com.paytm.dal;

import com.paytm.entity.Question;
import com.paytm.entity.User;

import java.util.List;

public interface QuestionDal
{
    User getUserByQuestionIdMethod(int id);
    Question getQuestionByQuestionIdMethod(int id);
    boolean AddQuestionMethod(Question ques  );
    boolean UpdateQuestionMethod(String question,String UpdateQuestion);
    boolean DeleteQuestionMethod(int Ques_Id);
    boolean checkExistingQuestionMethod(Question q);
    List<Question> showAllQuestionMethod(User user);
    //todo ekansh
    void incrementAnswersCountMethod(int qid);
}
