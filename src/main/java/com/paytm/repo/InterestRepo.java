package com.paytm.repo;

import com.paytm.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InterestRepo extends JpaRepository<Interest,Integer> {

    @Query("select i from Interest i where i.u_id = :uid and i.dept_id = :deptid")
    Interest getInterestByUIdByDeptId(@Param("uid") String uid, @Param("deptid") String deptid);
}
