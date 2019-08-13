package com.paytm.services;

import com.paytm.dal.UserDal;
import com.paytm.entity.User;
import com.paytm.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDal userDal;


    @Override
    public User findUserByNameService(String name) {
        return null;
    }


    @Override
    public User findUserByEmailService(String email) {

        System.out.println("Inside findUserByEmailService.");
       // return userRepo.findUserByEmail(email);
        return userDal.findUserByEmailMethod(email);
    }

    @Override
    public boolean deleteUserService(String email) {
        return false;
    }
}
