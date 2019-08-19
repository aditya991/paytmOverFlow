package com.paytm.repo;

import com.paytm.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InterestRepo extends JpaRepository<Interest,Integer> {

    @Transactional
    @Modifying
    @Query("delete from Interest i where i.u_id = :uid and i.dept_id = :deptid")
    public void removeInterestByUIdByDeptId(@Param("uid") int uid, @Param("deptid") int deptid);

    @Query("select i.dept_id from Interest i where i.u_id = :uid")
    public List<Integer> getDeptIdbyUId(@Param("uid") int uid);

    /*@Query("select i.dept_id from Interest i where i.dept_id = :uid")
    public Interest getInterestByDeptId(@Param("deptid") int deptid);*/

}