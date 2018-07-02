package com.almin.horimvplibrary.network.model;

import com.google.gson.annotations.SerializedName;

public class HeaderBean {
    /**
     * time_stamp : 20180110092058
     * token : 1515547091014377a4293f264b74984c
     */

    @SerializedName("time_stamp")
    private long timeStamp;
    private String token;
    private String passwd;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
