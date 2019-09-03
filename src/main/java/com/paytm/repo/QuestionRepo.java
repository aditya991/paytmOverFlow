package com.paytm.repo;

import com.paytm.entity.Question;
import com.paytm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question,Integer> {

        @Query("select q from Question q where q.user = :user")
        List<Question> getQuestionByUser(@Param("user") User user);

        @Query("select  q from Question  q where q.question=:question")
        Question getQuestionByName(@Param("question")String question);

        @Transactional
        @Modifying
        @Query("delete FROM Question q WHERE q.question_Id=:question_Id")
        void deleteQuestionById(@Param("question_Id") int question_Id);

        @Transactional
        @Modifying
        @Query("update Question q SET q.question =:UpdateQuestion WHERE q.question=:question")
        void updateQuestionByName(@Param("UpdateQuestion") String UpdateQuestion, @Param("question")String question);

        //todo ekansh
        @Transactional
        @Modifying
        @Query("update Question q SET q.answersCount = q.answersCount + 1 WHERE q.question_Id=:qid")
        void incrementAnswersCount(@Param("qid") int qid);

    /**
     * created by Aditya
     * @param id
     * @return User
     */
    @Query("select q.user from Question q where q.question_Id=:id")
    User getUserByQuestionId(@Param("id") int id);

    @Query("select q from Question q where q.question_Id=:id")
    Question getQuestionByQuestionId(@Param("id") int id);

        //select q.question from Question q , Interest i ,Dept d where q.user_u_id = i.u_id and i.dept_id = d.dept_id and q.department = d.dept_name;
//    @Query("select q from Question q where q.user = :user")
//    List<Question> getQuestionByUser(@Param("user") User user);

//    @Query("select  q from Question  q where q.question=:question")
//    Question getQuestionByName(@Param("question")String question);

//    @Transactional
//    @Modifying
//    @Query("delete FROM Question q WHERE q.question_Id=:Ques_Id")
//    void deleteQuestionById(@Param("Ques_Id")Integer Ques_Id);
//
//    @Transactional
//    @Modifying
//    @Query("update Question q SET q.question =:UpdateQuestion WHERE q.question=:question")
//    void updateQuestionByName(@Param("UpdateQuestion") String UpdateQuestion, @Param("question")String question);

    //select q.question from Question q , Interest i ,Dept d where q.user_u_id = i.u_id and i.dept_id = d.dept_id and q.department = d.dept_name;
}
