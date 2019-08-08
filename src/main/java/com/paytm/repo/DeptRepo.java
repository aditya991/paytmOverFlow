package com.paytm.repo;

import com.paytm.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DeptRepo extends JpaRepository<Dept,Integer> {


    @Query("select dept from Dept dept where dept.dept_name=:dept_name ")
    String findDeptByDeptName(@Param("dept_name") String dept_name);

}

