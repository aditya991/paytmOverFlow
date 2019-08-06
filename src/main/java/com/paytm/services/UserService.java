package com.paytm.services;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */

import com.paytm.entity.User;

public interface UserService
{
    User findUserByName(String name);
    User findUserByEmail(String email);
    boolean deleteUser(String email);
}
