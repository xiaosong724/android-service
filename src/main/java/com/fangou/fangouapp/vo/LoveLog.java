package com.fangou.fangouapp.vo;

import java.util.Date;
import java.util.List;

public class LoveLog {
    private int id;
    private String username;
    private String title;
    private String coversrc;
    private Date nowtime;
    private  String logtype;
    private String message;
    private  int viewcount;
    private int imgid;
    private List<String> imgpath;

    public LoveLog() {
    }

    public LoveLog(String username, String title, String coversrc, Date nowtime, String logtype, String message, int viewcount) {
        this.username = username;
        this.title = title;
        this.coversrc = coversrc;
        this.nowtime = nowtime;
        this.logtype = logtype;
        this.message = message;
        this.viewcount = viewcount;
    }

    public LoveLog(String username, String title, String coversrc, Date nowtime, String logtype, String message, int viewcount, int imgid, List<String> imgpath) {
        this.username = username;
        this.title = title;
        this.coversrc = coversrc;
        this.nowtime = nowtime;
        this.logtype = logtype;
        this.message = message;
        this.viewcount = viewcount;
        this.imgid = imgid;
        this.imgpath = imgpath;
    }

    public LoveLog(int id, String username, String title, String coversrc, Date nowtime, String logtype, String message, int viewcount, int imgid, List<String> imgpath) {
        this.id = id;
        this.username = username;
        this.title = title;
        this.coversrc = coversrc;
        this.nowtime = nowtime;
        this.logtype = logtype;
        this.message = message;
        this.viewcount = viewcount;
        this.imgid = imgid;
        this.imgpath = imgpath;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }


    public List<String> getImgpath() {
        return imgpath;
    }

    public void setImgpath(List<String> imgpath) {
        this.imgpath = imgpath;
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

    public int getViewcount() {
        return viewcount;
    }

    public void setViewcount(int viewcount) {
        this.viewcount = viewcount;
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
                ", viewcount=" + viewcount +
                ", imgid=" + imgid +
                ", imgpath=" + imgpath +
                '}';
    }
}
