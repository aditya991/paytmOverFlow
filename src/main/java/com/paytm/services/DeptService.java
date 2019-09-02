package com.paytm.services;

import com.paytm.entity.Dept;

import java.util.List;

public interface DeptService {
    Dept findDeptByNameService(String deptName);

    List<Dept> enterAllAvailableDeptService(List<String> deptNames);

    List<String> listAllDeptByNameService();
}
