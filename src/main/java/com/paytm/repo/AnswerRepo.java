package com.paytm.repo;
/*
 * @author: aditya10.kumar
 * @created: 09/08/19
 */
import com.paytm.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AnswerRepo extends JpaRepository<Answer,Integer>
{

    @Transactional
    @Modifying
    @Query("UPDATE Answer a SET a.answer=:answer, a.updated=CURRENT_TIMESTAMP WHERE a.answer_id=:answer_id")
    void updateAnswerByAnswerId(@Param("answer_id") int answer_id , @Param("answer") String answer);

    @Query("DELETE FROM Answer a WHERE a.answer_id=:answer_id")
    void deleteAnswerByAnswerId(@Param("answer_id") int answer_id);
}
