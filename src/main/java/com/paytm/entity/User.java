package com.paytm.entity;

import javax.persistence.*;

import java.util.Set;

/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
@Entity
public class User extends AbstractEntity {


    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id", updatable = false, nullable = false)

    private Integer u_id;
    private String u_name;
    private String password;

    @Column(unique = true)
    private String email;


    @Column(unique = true)
    private String phone;


    /*This is for creating a join on Dept and User
    @ManyToMany
    Set<Dept> likedDepartments;

    @ManyToMany
    @JoinTable(
            name = "interest",
            joinColumns = @JoinColumn(name = "u_id"),
            inverseJoinColumns = @JoinColumn(name = "dept_id")
    )*/


    @OneToOne
    private Dept dept;

    public Integer getU_id() {
        return u_id;
    }

    public void setU_id(Integer u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }
}