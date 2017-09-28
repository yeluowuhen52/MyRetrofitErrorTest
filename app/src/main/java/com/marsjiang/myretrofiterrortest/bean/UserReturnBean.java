package com.marsjiang.myretrofiterrortest.bean;

import java.io.Serializable;

/**
 * Created by Jiang on 2017-09-21.
 */

public class UserReturnBean implements Serializable {
    private String username;
    private String userpass;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpass() {
        return userpass;
    }

    public void setUserpass(String userpass) {
        this.userpass = userpass;
    }
}
