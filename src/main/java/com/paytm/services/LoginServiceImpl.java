package com.paytm.services;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */

import com.paytm.dal.UserDal;
import com.paytm.entity.Token;
import com.paytm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService
{

    @Autowired
    private UserDal userDal;

    @Override
    public User SessionValidate(String token) {
        User u=new User();
        return null; }

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
            if(DBPass.equals(password))
            {
                return true;
            }
        }
        catch (Exception e)
        {
            System.out.println("User not found");
        }
        return false;
    }

    @Override
    public boolean createTokenService(Token tok)
    {
        userDal.createTokenMethod(tok);
        return true;
    }

    @Override
    public User findUserByEmailService(String email)
    {
        User u= userDal.findUserByEmailMethod(email);
        return u;
    }
}