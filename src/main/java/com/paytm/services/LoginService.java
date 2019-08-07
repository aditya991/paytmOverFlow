package com.paytm.services;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
import com.paytm.entity.User;

public interface LoginService
{
    User SessionValidate(String token);
    boolean UserCredentials(String email, String password);
    boolean markSessionInactive(String token);
}
