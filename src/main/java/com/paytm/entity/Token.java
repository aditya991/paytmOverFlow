package com.paytm.entity;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */

import java.util.Date;

public class Token extends AbstractEntity
{
    private Integer token_id;
    private String token_no;
    private Date expiry_time;
    private Integer flag;

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

    public Date getExpiry_time() {
        return expiry_time;
    }

    public void setExpiry_time(Date expiry_time) {
        this.expiry_time = expiry_time;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}

