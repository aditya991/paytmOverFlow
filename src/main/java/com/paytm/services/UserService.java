package com.paytm.services;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
import com.paytm.entity.User;

public interface UserService {
    User findUserByNameService(String name);

    User findUserByEmailService(String email);

    User findUserByResetTokenService(String token);

    boolean deleteUserService(String email);

    void save(User user);
}

