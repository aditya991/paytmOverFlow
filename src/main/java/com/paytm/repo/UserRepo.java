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
    @Query("select u from User u where u.email = :email")
    User findUserByEmail(@Param("email") String email);

    @Query("select u from User u where u.phone= :phone")
    User findUserByPhone(@Param("phone") String phone);

    @Query("select u from User u where u.u_id=:uid")
    User findUserByUserId(@Param("uid") int uid);

    @Query("select u.password from User u where u.email=:email")
    String findPasswordByEmail(@Param("email") String email);

    @Query("select u from User u where u.resetToken=:token")
    User findUserByResetToken(@Param("token") String token);
//    @Query("select u.u_id from User u where u.email=:email")
//    int findUserIdByEmail(@Param("email") String email);

}


