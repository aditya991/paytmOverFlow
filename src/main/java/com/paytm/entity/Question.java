package com.paytm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
    private String Question;
    @Id
    @GeneratedValue
    private Integer Ques_Id;
    private String Department;

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
