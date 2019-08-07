package com.paytm.services;


import com.paytm.dal.UserDal;
import com.paytm.entity.Dept;
import com.paytm.entity.User;



public class SignupServiceImpl implements SignupService {


    private UserDal userDal = new UserDal();

    @Override
    public boolean createUser(String u_name, String email, String phone, String password ,String dept) {

        User user=new User();

        user.setU_name(u_name);
        user.setEmail(email);
        user.setPhone(phone);
        user.setPassword(password);
        user.setDept(new Dept(dept));


        userDal.createUserDal(user);

        return false;


    }




    @Override
    public boolean validUser(String email, String phone) {

        boolean a= userDal.validUserEmail(email) ;

        boolean b= userDal.validUserPhone(phone) ;


        return  (a && b);

    }
}





