package com.fangou.fangouapp.vo;

import java.util.Date;

public class LogComment {
    private int id;
    private int logid;
    private String logname;
    private String comment;
    private Date texttime;

    public LogComment() {
    }

    public LogComment(int logid, String logname, String comment, Date texttime) {
        this.logid = logid;
        this.logname = logname;
        this.comment = comment;
        this.texttime = texttime;
    }

    public LogComment(int id, int logid, String logname, String comment, Date texttime) {
        this.id = id;
        this.logid = logid;
        this.logname = logname;
        this.comment = comment;
        this.texttime = texttime;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTexttime() {
        return texttime;
    }

    public void setTexttime(Date texttime) {
        this.texttime = texttime;
    }

    @Override
    public String toString() {
        return "LogComment{" +
                "id=" + id +
                ", logname='" + logname + '\'' +
                ", comment='" + comment + '\'' +
                ", texttime=" + texttime +
                '}';
    }
}
