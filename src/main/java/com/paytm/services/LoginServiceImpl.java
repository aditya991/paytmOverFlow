package com.paytm.services;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
import com.paytm.dal.UserDal;
import com.paytm.entity.Token;
import com.paytm.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService
{



    private Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);
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
    public boolean markSessionInactiveService(String token)
    {
        userDal.markSessionInactivemethod(token);
        return false;
    }

    @Override
    public boolean isTokenActiveService(String token)
    {
        try{
            boolean flag=userDal.isTokenActiveMethod(token);
            LOG.info("flag {}",flag);
            return  flag;
        }catch (Exception e){
            LOG.error("Error occurred", e);
        }
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
            LOG.info("User not found");
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

    @Override
    public Token findTokenByUserService(User user) {
        return userDal.findTokenByUserMethod(user);
    }
}