package com.paytm.entity;

import javax.persistence.*;

@Entity
public class Dept extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "dept_id" ,updatable = false ,nullable = false)
    private Integer dept_id;

    @Column(unique = true)
    private String dept_name;

    public Dept(String dept_name) {
        this.dept_name = dept_name;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
}