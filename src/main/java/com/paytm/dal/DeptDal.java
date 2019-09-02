package com.paytm.dal;

import com.paytm.entity.Dept;

import java.util.List;

public interface DeptDal {
    Dept findDeptByNameMethod(String deptName);

    List<Dept> enterAllAvailableDeptMethod(List<String> deptNames);

    List<String> listAllDeptByNameMethod();
}
