package com.paytm.services;
import com.paytm.dal.QuestionDalImpl;
import com.paytm.entity.Interest;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.repo.QuestionRepo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.net.ssl.SSLEngine;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.servlet.http.HttpSession;


public class QuestionServiceImpl implements QuestionService{
   QuestionDalImpl ques=new QuestionDalImpl();
    @Override
    public void AddQuestionService(String department, String question, HttpSession session)
    {  UserServiceImpl userservice=new UserServiceImpl() ;
        User user=userservice.findUserByEmail(session.getAttribute("email"));
        Question q=new Question();
        q.setQuestion(question);
        q.setDepartment(department);
        q.setQuestion_Id(q.getQuestion_Id());
        q.setUser(user);

        ques.AddQuestionMethod(q,user);



    }

    @Override
    public boolean UpdateQuestionService(Integer Ques_Id,HttpSession session) {

    }

    @Override
    public boolean DeleteQuestionService(Integer Ques_Id,HttpSession session) {
        return false;
    }

    @Override
    public boolean ValidUser(String  question,HttpSession session) {
        UserServiceImpl userservice=new UserServiceImpl();
        User user=userservice.findUserByEmail(session.getAttribute("email"));
        Question ques=new Question();
        ques=getquestion_Idbyquestion(question);
    return ques.ValidUserMethod(ques.getQuestion_Id(),session);
    }
}
