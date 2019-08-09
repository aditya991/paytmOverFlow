package com.paytm.dal;

import com.paytm.entity.Dept;
import com.paytm.repo.DeptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

@Component
public class DeptDalImpl {

    @Autowired
    private DeptRepo deptRepo;

    @Autowired
    private EntityManagerFactory emf;

    public Dept findDeptByNameMethod(String deptName) {
        return deptRepo.findDeptByName(deptName);
    }

    public List<Dept> enterAllAvailableDeptMethod() { return deptRepo.showAllDept(); }
}