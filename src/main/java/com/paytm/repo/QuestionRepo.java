package com.paytm.repo;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface QuestionRepo extends JpaRepository<Question,Integer> {

    @Query("select q from Question q where q.user = :user")
    List<Question> getQuestionByUser(@Param("user") User user);

    @Query("select q.user from Question q where q.question_Id = :qid")
    User getUserByQuestionId(@Param("qid") int id);

    @Query("select  q from Question  q where q.question=:question")
    Question getQuestionByName(@Param("question")String question);

    @Transactional
    @Modifying
    @Query("delete FROM Question q WHERE q.question_Id=:Ques_Id")
    void deleteQuestionById(@Param("Ques_Id")Integer Ques_Id);

    @Transactional
    @Modifying
    @Query("update Question q SET q.question =:UpdateQuestion WHERE q.question=:question")
    void updateQuestionByName(@Param("UpdateQuestion") String UpdateQuestion, @Param("question")String question);

    //select q.question from Question q , Interest i ,Dept d where q.user_u_id = i.u_id and i.dept_id = d.dept_id and q.department = d.dept_name;
}