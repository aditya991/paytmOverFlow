package com.paytm.entity;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */
import javax.persistence.*;
@Entity
public class Answer extends AbstractEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ans_id" ,updatable = false ,nullable = false)
    private int answer_id;
    private String answer;

    @ManyToOne
    private User user;

    @ManyToOne
    private Question question;

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

//    @Override
//    public String toString() {
//        return "Answer{" +
//                "answer_id=" + answer_id +
//                ", answer='" + answer + '\'' +
//                ", user=" + user +
//                ", question=" + question +
//                '}';
//    }
}