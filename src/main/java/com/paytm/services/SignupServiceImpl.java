package com.paytm.services;

import com.paytm.dal.UserDal;
import com.paytm.entity.Dept;
import com.paytm.entity.User;
import com.paytm.repo.DeptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignupServiceImpl implements SignupService {

    @Autowired
    UserDal userDal ;


    @Autowired
    private DeptRepo deptRepo;



    @Override
    public boolean createUserService(String u_name, String email, String phone, String password , String dept) {

        System.out.println("inside create user function");


        //Dept d= new Dept(dept);


         Dept d=deptRepo.findDeptByName(dept);



        User user = new User();

        user.setU_name(u_name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setDept(d);


        userDal.createUserMethod(user);


        System.out.println("inside create user function final step");

        return false;
    }

    @Override
    public boolean checkExistingUserService(String email, String phone) {

       boolean a= !userDal.validUserEmailMethod(email) ;

        boolean b= !userDal.validUserPhoneMethod(phone) ;


        System.out.println("in valid user     "+a+"        "+ b);


        return  a && b;

    }
}