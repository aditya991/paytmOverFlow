package com.paytm.services;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */

import com.paytm.dal.UserDal;
import com.paytm.entity.User;
public class LoginServiceImpl implements LoginService
{
    public LoginServiceImpl() {}
    UserDal userDal=new UserDal();

    @Override
    public User SessionValidate(String token)
    {
        User u=new User();
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

    @Override
    public User findUserByUserIdService(int id) {
        User u=userDal.findUserByUserIdMethod(id);
        return u;
    }

    @Override
    public int findUserIdByTokenService(String token)
    {
        int id=userDal.findUserIdByTokenMethod(token);
        return id;
    }

    @Override
    public boolean UserAuthenticationService(String email, String password)
    {
        try
        {
            String DBPass = userDal.findPasswordByEmailMethod(email);
        }
        catch (Exception e)
        {
            System.out.println("User not found");
        }
        return true;
    }
}
