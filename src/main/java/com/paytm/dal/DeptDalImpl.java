package com.paytm.dal;

import com.paytm.configuration.DBConfiguration;
import com.paytm.entity.Dept;
import com.paytm.repo.DeptRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class DeptDalImpl implements DeptDal {

    private static final Logger LOG = LoggerFactory.getLogger(DBConfiguration.class);

    @Autowired
    private DeptRepo deptRepo;

    @Autowired
    private EntityManagerFactory emf;

    @Override
    public Dept findDeptByNameMethod(String deptName) {
        return deptRepo.findDeptByName(deptName);
    }

    @Override
    public List<Dept> enterAllAvailableDeptMethod(List<String> deptNames) { return deptRepo.showAllDept(deptNames); }


    @Override
    public List<String> listAllDeptByNameMethod()
    {
        return deptRepo.listAllDeptByName();

    }




}