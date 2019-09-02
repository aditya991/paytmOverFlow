package com.paytm.repo;
/*
 * @author: aditya10.kumar
 * @created: 09/08/19
 */
import com.paytm.entity.Answer;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AnswerRepo extends JpaRepository<Answer,Integer>
{
    @Transactional
    @Modifying
    @Query("UPDATE Answer a SET a.answer=:answer ,a.updated=CURRENT_TIMESTAMP WHERE a.answer_id=:answer_id")
    void updateAnswerByAnswerId(@Param("answer_id") int answer_id,@Param("answer") String answer);
//
    @Transactional
    @Modifying
    @Query("delete from Answer a where a.answer_id=:answer_id")
    void deleteAnswerByAnswerId(@Param("answer_id") int answer_id);

    @Query("select a from Answer a where a.user=:user")
    List<Answer> findAllAnswerByUser(@Param("user") User user);

    @Query("select a from Answer a where a.question=:ques")
    List<Answer> findAllAnswerByQuestion(@Param("ques") Question ques);

    @Transactional
    @Modifying
    @Query("delete from Answer a where a.question=:question")
    void deleteAnswerByQuestion(@Param("question") Question question);

}
