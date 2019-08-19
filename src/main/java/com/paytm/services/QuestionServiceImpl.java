package com.paytm.services;

import com.paytm.dal.DeptDalImpl;
import com.paytm.dal.QuestionDalImpl;
import com.paytm.entity.Dept;
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

    @Autowired
    DeptDalImpl deptDal;

    @Override
    public boolean AddQuestionService(String department, String question ,String email) {
        User user=userService.findUserByEmailService(email);
        Dept d =deptDal.findDeptByNameMethod(department);

        Question q=new Question();
        q.setQuestion(question);
        q.setDept(d);
        q.setUser(user);

        return questionDal.AddQuestionMethod(q);
    }

    @Override
    public boolean UpdateQuestionService(String question,String UpdateQuestion) {
        questionDal.UpdateQuestionMethod(question,UpdateQuestion);
        return true;
    }

    @Override
    public boolean DeleteQuestionService(String question) {
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

    /**
     * @created by: Aditya
     * @param id
     * @return User
     */
    @Override
    public User getUserByQuestionIdService(int id) {
        return  questionDal.getUserByQuestionIdMethod(id);
    }
}