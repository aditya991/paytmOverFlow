package com.paytm.services;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
import com.paytm.entity.User;

public interface LoginService
{
    User SessionValidate(String token);
    boolean UserCredential(String email, String password);
}
