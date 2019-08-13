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

    @Query("select  k from Question  k where k.question=:question")
    Question getQuestionByQuestion(@Param("question")String question);

    @Transactional
    @Modifying
    @Query("delete FROM Question t WHERE t.question_Id=:Ques_Id")
    void deleteByQuestion_Id(@Param("Ques_Id")Integer Ques_Id);
    @Transactional
    @Modifying
    @Query("update Question t SET t.question =:UpdateQuestion WHERE t.question=:question")
    void removeQuestionByQuestion(@Param("UpdateQuestion") String UpdateQuestion, @Param("question")String question);



}