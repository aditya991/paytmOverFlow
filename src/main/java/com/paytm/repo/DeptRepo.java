package com.paytm.repo;

import com.paytm.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeptRepo extends JpaRepository<Dept,Integer> {


}
