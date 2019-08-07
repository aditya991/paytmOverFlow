package com.paytm.entity;

import javax.persistence.*;
<<<<<<< HEAD
import java.util.Set;
=======
>>>>>>> 8cd48fd461a12d87e7dc0ccac7d1fe368b6b21e9

@Entity
public class Dept extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer dept_id;
<<<<<<< HEAD
    @Column(unique = true)
    private String dept_name;

    //Right now I am not creating join instead I am explicitly creating a table called Interest
    /*This is for creating a join on Dept and User*/
    /*@ManyToMany
    Set<User> likes;*/

    public Dept(String dept_name) {
        this.dept_name = dept_name;
    }

=======

    @Column(unique = true)
    private String dept_name;

>>>>>>> 8cd48fd461a12d87e7dc0ccac7d1fe368b6b21e9
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
<<<<<<< HEAD
=======

>>>>>>> 8cd48fd461a12d87e7dc0ccac7d1fe368b6b21e9
}