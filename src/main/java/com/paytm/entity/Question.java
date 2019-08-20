package com.paytm.entity;


        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.Id;

        import javax.persistence.*;

/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
@Entity
@Table(name = "Question", schema = "paytmDB")
public class Question extends AbstractEntity {
    private String question;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_Id", updatable = false, nullable = false)
    private Integer question_Id;

    @ManyToOne
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dept_id", nullable = false)
    private Dept dept;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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