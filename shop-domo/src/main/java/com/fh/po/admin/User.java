package com.fh.po.admin;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {


    private static final long serialVersionUID = -8154954927149676187L;
    private  Integer userid;
    private  String username;
    private  String userpass;
    private String relname;

    private String verifyCode;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date lastLoginTime;
    private  Integer todayLoginCount;
    private Integer loginErrorCount;

    private int status;

    private  Date loginErrorTime;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

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

    public String getRelname() {
        return relname;
    }

    public void setRelname(String relname) {
        this.relname = relname;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getTodayLoginCount() {
        return todayLoginCount;
    }

    public void setTodayLoginCount(Integer todayLoginCount) {
        this.todayLoginCount = todayLoginCount;
    }

    public Integer getLoginErrorCount() {
        return loginErrorCount;
    }

    public void setLoginErrorCount(Integer loginErrorCount) {
        this.loginErrorCount = loginErrorCount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getLoginErrorTime() {
        return loginErrorTime;
    }

    public void setLoginErrorTime(Date loginErrorTime) {
        this.loginErrorTime = loginErrorTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", userpass='" + userpass + '\'' +
                ", relname='" + relname + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                ", todayLoginCount=" + todayLoginCount +
                ", loginErrorCount=" + loginErrorCount +
                ", status=" + status +
                ", loginErrorTime=" + loginErrorTime +
                '}';
    }
}
