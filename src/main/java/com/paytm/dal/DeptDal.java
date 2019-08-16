package com.paytm.dal;

import com.paytm.entity.Dept;
import com.paytm.repo.DeptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class DeptDal {

    @Autowired
    private DeptRepo deptRepo;

    @Autowired
    private EntityManagerFactory emf;

    public Dept findDeptByNameMethod(String deptName) {
        return deptRepo.findDeptByName(deptName);
    }

    public List<Dept> getAllAvailableDeptMethod(List<String> deptNames) { return deptRepo.getAllAvailableDept(deptNames); }

    public List<String> listAllDeptByNameMethod() { return deptRepo.listAllDeptByName(); }
}