package com.paytm.services;

import com.paytm.dal.DeptDal;
import com.paytm.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService{

    @Autowired
    DeptDal deptDal;

    @Override
    public Dept findDeptByNameService(String deptName) {
        return deptDal.findDeptByNameMethod(deptName);
    }

    @Override
    public List<Dept> getAllAvailableDeptService(List<String> interestNames) {
        return deptDal.getAllAvailableDeptMethod(interestNames);
    }
}
