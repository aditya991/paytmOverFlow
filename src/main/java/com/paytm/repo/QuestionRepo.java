package com.paytm.repo;
import com.paytm.entity.Question;
import com.paytm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepo extends JpaRepository<Question,Integer> {

    @Query("select q.question from Question q where q.user = :user")
    List<String> getQuestionByUser(@Param("user") User user);


}
