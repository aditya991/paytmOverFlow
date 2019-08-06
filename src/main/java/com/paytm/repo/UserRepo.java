package com.paytm.repo;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
import com.paytm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends JpaRepository<User,Integer>
{
    @Query("select u.name from User u where u.email = :emailaddress")
    String findByEmailAddress(@Param("emailaddress") String emailaddress);
}


