package com.fangou.fangouapp.vo;


public class Logimg {
    private int imgid;
    private int logid;
    private String imgpath;
    private String title;
    private String datetime;


    public Logimg() {
    }

    public Logimg(int imgid, int logid, String imgpath, String title) {
        this.imgid = imgid;
        this.logid = logid;
        this.imgpath = imgpath;
        this.title = title;
    }

    public Logimg(int imgid, int logid, String imgpath, String title, String datetime) {
        this.imgid = imgid;
        this.logid = logid;
        this.imgpath = imgpath;
        this.title = title;
        this.datetime = datetime;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public int getLogid() {
        return logid;
    }

    public void setLogid(int logid) {
        this.logid = logid;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Logimg{" +
                "imgid=" + imgid +
                ", logid=" + logid +
                ", imgpath='" + imgpath + '\'' +
                ", title='" + title + '\'' +
                ", datetime=" + datetime +
                '}';
    }
}
