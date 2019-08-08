package com.paytm.repo;
import com.paytm.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepo extends JpaRepository<Question,Integer> {

    @Query("select u.u_name from Question u where u.question_Id=:Q_Id")
    Question getUsernameByquestion_Id(@Param("Q_Id")String question_Id);
    @Query("select u.Ques_Id from Question u where u.question=:Ques")
    Question getquestion_Idbyquestion(@Param("Ques")String question);

}
