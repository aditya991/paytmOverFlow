package com.paytm.services;

import com.paytm.configuration.DBConfiguration;
import com.paytm.dal.DeptDalImpl;
import com.paytm.dal.QuestionDalImpl;
import com.paytm.entity.Dept;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.repo.QuestionRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestionServiceImpl implements QuestionService {

    private static final Logger LOG = LoggerFactory.getLogger(DBConfiguration.class);

    @Autowired
    QuestionDalImpl questionDal;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    QuestionRepo questionRepo;

    @Autowired
    DeptDalImpl deptDal;

    /**
     * This service takes
     *
     * @param Department
     * @param Question
     * @return
     */
    @Override
    public boolean AddQuestionService(String department, String question, String email) {

        LOG.info("in question service");
        LOG.info(department + "      " + question + "          " + email);


        User user = userService.findUserByEmailService(email);
        Dept d = deptDal.findDeptByNameMethod(department);

        Question q = new Question();
        q.setQuestion(question);
        q.setDept(d);
        q.setUser(user);

        return questionDal.AddQuestionMethod(q);

    }

    @Override
    public boolean UpdateQuestionService(String question, String UpdateQuestion) {
        questionDal.UpdateQuestionMethod(question, UpdateQuestion);
        return true;
    }

    @Override
    public boolean DeleteQuestionService(String question) {
        Question ques = questionRepo.getQuestionByName(question);
        questionDal.DeleteQuestionMethod(ques.getQuestion_Id());
        return true;
    }

    @Override
    public List<Question> showAllQuestionService(String email) {

        User user = userService.findUserByEmailService(email);
        LOG.info("in showAllquestion   " + user.getU_id());

        List<Question> l = questionDal.showAllQuestionMethod(user);

        return l;
    }

    @Override
    public boolean ValidUser(Integer Ques_Id) {
        return false;
    }

    /**
     * @param id
     * @return User
     * @created by: Aditya
     */
    @Override
    public User getUserByQuestionIdService(int id) {
        return questionDal.getUserByQuestionIdMethod(id);
    }

    @Override
    public Question getQuestionByQuestionIdService(int id) {
        return questionDal.getQuestionByQuestionIdMethod(id);
    }
}
