package com.fangou.fangouapp.vo;

public class LogUser {
    private int id;
    private String logname;
    private String logpassword;
    private int logcount;

    public LogUser() {
    }

    public LogUser(String logname, String logpassword, int logcount) {
        this.logname = logname;
        this.logpassword = logpassword;
        this.logcount = logcount;
    }

    public LogUser(int id, String logname, String logpassword, int logcount) {
        this.id = id;
        this.logname = logname;
        this.logpassword = logpassword;
        this.logcount = logcount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getlogpassword() {
        return logpassword;
    }

    public void setlogpassword(String logpassword) {
        this.logpassword = logpassword;
    }

    public int getLogcount() {
        return logcount;
    }

    public void setLogcount(int logcount) {
        this.logcount = logcount;
    }

    @Override
    public String toString() {
        return "LogUser{" +
                "id=" + id +
                ", logname='" + logname + '\'' +
                ", logpassword='" + logpassword + '\'' +
                ", logcount=" + logcount +
                '}';
    }
}
