package com.paytm.repo;
import com.paytm.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepo extends JpaRepository<Question,Integer> {

    @Query("select u.u_name from Question u where u.Ques_Id=:Ques_Id")
    Question getUsernameByQues_Id(@Param("Ques_Id")Integer Ques_Id);
    @Query("select u.Ques_Id from Question u where u.Question=:Ques")

}
