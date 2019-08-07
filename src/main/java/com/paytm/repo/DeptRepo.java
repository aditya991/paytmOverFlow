package com.paytm.repo;

import com.paytm.entity.Dept;
<<<<<<< HEAD
import com.paytm.entity.Interest;
=======
>>>>>>> 8cd48fd461a12d87e7dc0ccac7d1fe368b6b21e9
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

<<<<<<< HEAD
import java.util.List;

public interface DeptRepo extends JpaRepository<Dept,Integer> {

    @Query("select d.dept_name from Dept d where d.dept_id = :deptid")
    List<String> getDeptNameByDeptId(@Param("deptid") String deptid);

}
=======
public interface DeptRepo extends JpaRepository<Dept,Integer> {


    @Query("select dept from Dept dept where dept.dept_name=:dept_name ")
    String findDeptByDeptName(@Param("dept_name") String dept_name);

}
>>>>>>> 8cd48fd461a12d87e7dc0ccac7d1fe368b6b21e9
