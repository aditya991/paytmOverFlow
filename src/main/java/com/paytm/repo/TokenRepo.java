package com.paytm.repo;
/*
 * @author: aditya10.kumar
 * @created: 07/08/19
 */

import com.paytm.entity.Token;
import com.paytm.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface TokenRepo extends JpaRepository<Token,Integer>
{
    @Query("select t.user from Token t where t.token_no=:token")
    User findUserIdByToken(@Param("token") String token);

    @Query("select t.flag from Token t where t.token_no=:token")
    boolean isSessionActive(@Param("token") String token);

    //without these annotations DML statements won't work
    @Transactional
    @Modifying
    @Query("UPDATE Token t SET t.flag = false , t.updated=CURRENT_TIMESTAMP WHERE t.token_no=:token")
    void markSessionInactive(@Param("token") String token);

    @Query("select t from Token t where t.user=:user and t.flag=1")
    Token findTokenByUser(@Param("user") User user);
}
