package com.paytm.services;



import com.paytm.dal.DeptDalImpl;
import com.paytm.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {


    @Autowired
    private DeptDalImpl deptDal;


    @Override
    public Dept findDeptByNameService(String deptName)
    {
       return deptDal.findDeptByNameMethod(deptName);
    }


    @Override
    public List<Dept> enterAllAvailableDeptService(List<String> deptNames)
    {
        return deptDal.enterAllAvailableDeptMethod(deptNames);
    }


    @Override
    public List<String> listAllDeptByNameService()
    {
        return deptDal.listAllDeptByNameMethod();
    }

}
