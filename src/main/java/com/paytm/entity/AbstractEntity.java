package com.paytm.entity;
/*
 * @author: aditya10.kumar
 * @created: 06/08/19
 */

import java.util.Date;

public class AbstractEntity {

    private Date created;
    private Date updated;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
