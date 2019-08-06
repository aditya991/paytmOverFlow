package com.paytm.entity;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */

public class Token extends AbstractEntity {


    private Integer token_id;
    private String token_number;
    private Integer flag;


    public Integer getToken_id() {
        return token_id;
    }

    public void setToken_id(Integer token_id) {
        this.token_id = token_id;
    }

    public String getToken_number() {
        return token_number;
    }

    public void setToken_number(String token_number) {
        this.token_number = token_number;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
