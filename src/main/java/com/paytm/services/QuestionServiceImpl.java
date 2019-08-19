package com.paytm.services;
<<<<<<< HEAD
import com.paytm.dal.DeptDalImpl;
import com.paytm.dal.QuestionDalImpl;
import com.paytm.entity.Dept;
import com.paytm.entity.Interest;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.repo.QuestionRepo;
=======
import com.paytm.dal.QuestionDal;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import com.paytm.repo.UserRepo;
>>>>>>> dfb22dd0788400655c45c1c7c01293a985c74ae4
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

<<<<<<< HEAD

=======
>>>>>>> dfb22dd0788400655c45c1c7c01293a985c74ae4
@Service
public class QuestionServiceImpl implements QuestionService{


    @Autowired
    QuestionDalImpl questionDal;

    @Autowired
    UserServiceImpl userService;
    @Autowired
    QuestionRepo questionRepo;

    @Autowired
<<<<<<< HEAD
    DeptDalImpl deptDal;
=======
    private QuestionDal questionDal;

//    @Autowired
//    private
>>>>>>> dfb22dd0788400655c45c1c7c01293a985c74ae4

    @Override
    public boolean AddQuestionService(String department, String question ,String email)
    {

        System.out.println("in question service");
        System.out.println(department+"      "+ question+"          "+ email);



        User user=userService.findUserByEmailService(email);
        Dept d =deptDal.findDeptByNameMethod(department);

        System.out.println(user);

        Question q=new Question();
        q.setQuestion(question);
        q.setDept(d);
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

<<<<<<< HEAD

}
=======
    /**
     * @created by: Aditya
     * @param id
     * @return User
     */
    @Override
    public User getUserByQuestionIdService(int id)
    {
        return  questionDal.getUserByQuestionIdMethod(id);
    }
}
>>>>>>> dfb22dd0788400655c45c1c7c01293a985c74ae4
