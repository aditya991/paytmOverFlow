package com.paytm.services;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
import com.paytm.entity.Token;
import com.paytm.entity.User;

public interface LoginService
{
    User SessionValidate(String token);
    boolean UserCredentials(String email, String password);
    boolean markSessionInactiveService(String token);
    User findUserByUserIdService(int id);
    int findUserIdByTokenService(String token);
    boolean UserAuthenticationService(String email, String password);
    boolean createTokenService(Token tok);
    User findUserByEmailService(String email);
    boolean isTokenActiveService(String token);
    Token findTokenByUserService(User user);
}
