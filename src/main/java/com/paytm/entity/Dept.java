package com.paytm.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Dept extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "dept_id" ,updatable = false ,nullable = false)
    private Integer dept_id;


    @Column(unique = true)
    private String dept_name;

    //Right now I am not creating join instead I am explicitly creating a table called Interest
    /*This is for creating a join on Dept and User*/
    /*@ManyToMany
    Set<User> likes;*/

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "dept")
    private List<Question> questions = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Dept() {
        this.dept_name = "name not given";
    }

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