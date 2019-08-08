package com.paytm.services;
import com.paytm.dal.QuestionDalImpl;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.repo.UserRepo;
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
        q.setQues_Id(q.getQues_Id());
        q.setUser(user);

        ques.AddQuestionDal(q,user);



    }

    @Override
    public boolean UpdateQuestionService(Integer Ques_Id,HttpSession session) {

    }

    @Override
    public boolean DeleteQuestionService(Integer Ques_Id,HttpSession session) {
        return false;
    }

    @Override
    public boolean ValidUser(Integer Ques_Id,HttpSession session) {
        UserServiceImpl userservice=new UserServiceImpl();
        User user=userservice.findUserByEmail(session.getAttribute("email"));
    return ques.ValidUserDal(Ques_Id,user);
    }
}
