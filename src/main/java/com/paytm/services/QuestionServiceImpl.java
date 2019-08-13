package com.paytm.services;
import com.paytm.dal.QuestionDal;
import com.paytm.dal.QuestionDalImpl;
import com.paytm.entity.Dept;
import com.paytm.entity.Interest;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.repo.QuestionRepo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.net.ssl.SSLEngine;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpSession;
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
    public void AddQuestionService(String department, String question ,String email)
    {

        System.out.println("in question service");
        System.out.println(department+"      "+ question+"          "+ email);



        User user=userService.findUserByEmailService(email);


        System.out.println(user);

        Question q=new Question();
        q.setQuestion(question);
        q.setDepartment(department);

        q.setUser(user);

        questionDal.AddQuestionMethod(q);




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
        Question ques=questionRepo.getQuestionByQuestion(question);
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