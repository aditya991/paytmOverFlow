package com.paytm.services;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */

import com.paytm.entity.User;

public class LoginServiceImpl implements LoginService
{
    public LoginServiceImpl() {}

    @Override
    public User SessionValidate(String token) {
        return null;
    }

    @Override
    public boolean UserCredentials(String email, String password) {
        return false;
    }

    @Override
    public boolean markSessionInactive(String token) {
        return false;
    }
}
