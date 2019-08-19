package com.paytm.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Interest extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer interest_id;
    private Integer u_id;
    private Integer dept_id;
    private boolean ownDept;
    private static int  count = 1;

    public Interest() {}

    public Interest(Integer u_id, Integer dept_id) {
        this.u_id = u_id;
        this.dept_id = dept_id;
        if(count == 1) {
            ownDept = true;
            count--;
        }
        else
            ownDept = false;
    }

    public boolean isOwnDept() {
        return ownDept;
    }

    public Integer getInterest_id() {
        return interest_id;
    }

    public void setInterest_id(Integer interest_id) {
        this.interest_id = interest_id;
    }

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

}