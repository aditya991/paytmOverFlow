package com.paytm.services;

import com.paytm.configuration.DBConfiguration;
import com.paytm.dal.DeptDalImpl;
import com.paytm.dal.UserDal;
import com.paytm.entity.Dept;
import com.paytm.entity.User;
import com.paytm.repo.DeptRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SignupServiceImpl implements SignupService {

    private static final Logger LOG = LoggerFactory.getLogger(DBConfiguration.class);

    @Autowired
    UserDal userDal ;

    @Autowired
    DeptDalImpl deptDal;

    @Autowired
    DeptRepo deptRepo;




    public List<String> listAllDeptByNameService()
    {
       return deptDal.listAllDeptByNameMethod();
    }



    @Override
    public boolean createUserService(String u_name, String email, String phone, String password , String dept) {

        LOG.info("inside create user function");






         Dept d=deptRepo.findDeptByName(dept);

        User user = new User();

        user.setU_name(u_name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setDept(d);


        userDal.createUserMethod(user);


        LOG.info("inside create user function final step");

        return false;
    }

    @Override
    public boolean checkExistingUserService(String email, String phone) {

       boolean a= !userDal.validUserEmailMethod(email) ;

        boolean b= !userDal.validUserPhoneMethod(phone) ;


        LOG.info("in valid user     "+a+"        "+ b);


        return  a && b;

    }
}