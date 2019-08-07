package com.paytm.services;

public interface SignupService {



    boolean validUser(String email,String phone);

    boolean createUser(String u_name, String email,String phone,String password ,String dept );

}
