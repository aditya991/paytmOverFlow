package com.paytm.entity;

import javax.persistence.*;

@Entity
public class Question {
    private String Question;
    @Id
    @GeneratedValue
    private Integer Ques_Id;
    private String Department;
    @ManyToOne
    @JoinColumn(name="u_name")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public Integer getQues_Id() {
        return Ques_Id;
    }

    public void setQues_Id(Integer ques_Id) {
        Ques_Id = ques_Id;
    }
}
