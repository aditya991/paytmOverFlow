package com.paytm.repo;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepo extends JpaRepository<Question,Integer> {

 //   @Query("select u.user from Question u where u.question_Id=:Q_Id")
   // User  (@Param("Q_Id")String question_Id);
    @Query("select u.Ques_Id from Question u where u.question=:Ques")
    Integer getQuestionByQuestion_Id(@Param("Ques")String question);


}
