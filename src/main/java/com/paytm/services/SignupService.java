package com.paytm.services;

public interface SignupService {


    boolean checkExistingUserService(String email, String phone);

    boolean createUserService(String u_name, String email, String phone, String password , String dept );


}
