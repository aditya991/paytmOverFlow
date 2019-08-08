package com.paytm.dal;

import com.paytm.entity.Dept;
import com.paytm.repo.DeptRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class DeptDalImpl {

    @Autowired
    private DeptRepo deptRepo;

    public Dept findDeptByName(String deptName)
    {
        return deptRepo.findDeptByDeptName(deptName);
    }
}
