package com.paytm.entity;

import javax.persistence.*;

@Entity
public class Question {
    private String question;
    @Id
    @GeneratedValue
    private Integer question_Id;
    private String department;

    //@ManyToOne
   // @JoinColumn(name="u_name")
    //private User user;

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getQuestion_Id() {
        return question_Id;
    }

    public void setQuestion_Id(Integer question_Id) {
        this.question_Id = question_Id;
    }
}
