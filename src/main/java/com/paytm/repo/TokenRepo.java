package com.paytm.repo;
/*
 * @author: aditya10.kumar
 * @created: 07/08/19
 */

import com.paytm.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TokenRepo extends JpaRepository<Token,Integer>
{
    @Query("select t.user from Token t where t.token_no=:token")
    int findUserIdByToken(@Param("token") String token);
}
