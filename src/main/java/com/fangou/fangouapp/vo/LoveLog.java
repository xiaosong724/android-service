package com.fangou.fangouapp.vo;

import java.util.Date;

public class LoveLog {
    private int id;
    private String username;
    private String title;
    private String coversrc;
    private Date nowtime;
    private  String logtype;
    private String message;
    private  String imgsrc;

    public LoveLog() {
    }

    public LoveLog(String username, String title, String coversrc, Date nowtime, String logtype, String message, String imgsrc) {
        this.username = username;
        this.title = title;
        this.coversrc = coversrc;
        this.nowtime = nowtime;
        this.logtype = logtype;
        this.message = message;
        this.imgsrc = imgsrc;
    }

    public LoveLog(int id, String username, String title, String coversrc, Date nowtime, String logtype, String message, String imgsrc) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.coversrc = coversrc;
        this.nowtime = nowtime;
        this.logtype = logtype;
        this.message = message;
        this.imgsrc = imgsrc;
    }

    public String getCoversrc() {
        return coversrc;
    }

    public void setCoversrc(String coversrc) {
        this.coversrc = coversrc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getNowtime() {
        return nowtime;
    }

    public void setNowtime(Date nowtime) {
        this.nowtime = nowtime;
    }

    public String getLogtype() {
        return logtype;
    }

    public void setLogtype(String logtype) {
        this.logtype = logtype;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    @Override
    public String toString() {
        return "LoveLog{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", title='" + title + '\'' +
                ", coversrc='" + coversrc + '\'' +
                ", nowtime=" + nowtime +
                ", logtype='" + logtype + '\'' +
                ", message='" + message + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                '}';
    }
}
