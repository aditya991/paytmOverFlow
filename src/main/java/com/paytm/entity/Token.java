package com.paytm.entity;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */

import javax.persistence.*;
import java.util.Date;

@Entity
public class Token extends AbstractEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "token_id", updatable = false, nullable = false)
    private Integer token_id;

    @Column(unique = true)
    private String token_no;
     private boolean flag;

    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getToken_id() {
        return token_id;
    }

    public void setToken_id(Integer token_id) {
        this.token_id = token_id;
    }

    public String getToken_no() {
        return token_no;
    }

    public void setToken_no(String token_no) {
        this.token_no = token_no;
    }


    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}