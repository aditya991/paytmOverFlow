package com.paytm.services;
import com.paytm.dal.QuestionDalImpl;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.repo.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionServiceImpl implements QuestionService{


    @Autowired
    QuestionDalImpl questionDal;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    QuestionRepo questionRepo;


    @Override
    public boolean AddQuestionService(String department, String question ,String email)
    {

        System.out.println("in question service");
        System.out.println(department+"      "+ question+"          "+ email);



        User user=userService.findUserByEmailService(email);


        System.out.println(user);

        Question q=new Question();
        q.setQuestion(question);
        q.setDepartment(department);

        q.setUser(user);

         return questionDal.AddQuestionMethod(q);




    }


    @Override
    public boolean UpdateQuestionService(String question,String UpdateQuestion)
    {
        questionDal.UpdateQuestionMethod(question,UpdateQuestion);
        return true;
    }

    @Override
    public boolean DeleteQuestionService(String question)
    {
        Question ques=questionRepo.getQuestionByName(question);
        questionDal.DeleteQuestionMethod(ques.getQuestion_Id());
        return true;
    }



    @Override
    public List<Question> showAllQuestionService(String email) {

        User user=userService.findUserByEmailService(email);
        System.out.println("in showAllquestion   "+user.getU_id());

        List<Question> l= questionDal.showAllQuestionMethod(user);


        return l;

    }


}