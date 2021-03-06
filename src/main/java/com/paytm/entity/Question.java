package com.paytm.entity;

import javax.persistence.*;

/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
@Entity
public class Question extends AbstractEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_Id", updatable = false, nullable = false)
    private Integer question_Id;
    private String question;

    //todo ekansh
    private int answersCount;

    @ManyToOne
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dept_id", nullable = false)
    private Dept dept;

    public Integer getQuestion_Id() {
        return question_Id;
    }

    public void setQuestion_Id(Integer question_Id) {
        this.question_Id = question_Id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public int getAnswersCount() {
        return answersCount;
    }

    public void setAnswersCount(int answersCount) {
        this.answersCount = answersCount;
    }
}