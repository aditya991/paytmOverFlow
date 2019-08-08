package com.paytm.services;


import com.paytm.dal.UserDal;
import com.paytm.entity.Dept;
import com.paytm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SignupServiceImpl implements SignupService {

    @Autowired
    UserDal userDal ;

    @Override
    public boolean createUser(String u_name, String email, String phone, String password ,String dept) {

        System.out.println("inside create user function");


        Dept d= new Dept(dept);

        //d.setDept_id(1);

        User user=new User();

        user.setU_name(u_name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setCreated(new Date());
        user.setUpdated(new Date());
        user.setDept(d);



        userDal.createUserDal(user);


        System.out.println("inside create user function final step");

        return false;


    }




    @Override
    public boolean validUser(String email, String phone) {

       boolean a= userDal.validUserEmail(email) ;

        boolean b= userDal.validUserPhone(phone) ;


        System.out.println("in valid user     "+a+"        "+ b);


        return  a && b;

    }
}





