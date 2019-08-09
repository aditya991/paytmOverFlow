package com.paytm.services;
import com.paytm.dal.QuestionDalImpl;
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


@Service
public class QuestionServiceImpl implements QuestionService{


    @Autowired
   QuestionDalImpl questionDal;

   @Autowired
   UserServiceImpl userService;
   @Autowired
   QuestionRepo questionRepo;


    @Override
    public boolean UpdateQuestionService(String Ques, HttpSession session) {
        return false;
    }

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
    public boolean DeleteQuestionService(Integer Ques_Id,HttpSession session) {
        return false;
    }

    @Override
    public boolean ValidUser(String  question,String email) {

        User user=userService.findUserByEmailService(email);
        Question ques=new Question();
        ques=questionRepo.getquestion_Idbyquestion(question);
    return questionDal.ValidUserMethod(ques.getQuestion_Id(),user);
    return  false;
    }
}
