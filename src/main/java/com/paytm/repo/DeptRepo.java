package com.paytm.repo;

import com.paytm.entity.Dept;
import com.paytm.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface DeptRepo extends JpaRepository<Dept,Integer>
{

    @Query("select d.dept_name from Dept d where d.dept_id = :deptid")
    List<String> getDeptNameByDeptId(@Param("deptid") String deptid);

    @Query("select dept from Dept dept where dept.dept_name=:dept_name ")
    Dept findDeptByDeptName(@Param("dept_name") String dept_name);

    /*@Query("select * from Dept")
    List<Dept> showAllDept();*/


}