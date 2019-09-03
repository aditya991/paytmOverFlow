package com.paytm.services;

import com.paytm.configuration.DBConfiguration;
import com.paytm.dal.UserDal;
import com.paytm.entity.User;
import com.paytm.repo.UserRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(DBConfiguration.class);

    @Autowired
    UserDal userDal;

    @Autowired
    UserRepo userRepo;

    @Override
    public User findUserByNameService(String name) {
        return null;
    }


    @Override
    public User findUserByEmailService(String email) {

        LOG.info("Inside findUserByEmailService.");
        // return userRepo.findUserByEmail(email);
        return userDal.findUserByEmailMethod(email);
    }

    @Override
    public boolean deleteUserService(String email) {
        return false;
    }

    //todo ekansh
    @Override
    public User findUserByResetTokenService(String token) {
        return userDal.findUserByResetTokenMethod(token);
    }

    //todo ekansh
    @Override
    public void save(User user) {
        userRepo.save(user);
    }
}
