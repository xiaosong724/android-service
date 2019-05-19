package com.fangou.fangouapp.vo;

public class Loveimg {
    private int imgid;
    private int logid;
    private String imgpath;

    public Loveimg() {
    }

    public Loveimg(int logid, String imgpath) {
        this.logid = logid;
        this.imgpath = imgpath;
    }

    public Loveimg(int imgid, int logid, String imgpath) {
        this.imgid = imgid;
        this.logid = logid;
        this.imgpath = imgpath;
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

    public void setLogid(int lgoid) {
        this.logid = lgoid;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    @Override
    public String toString() {
        return "Loveimg{" +
                "imgid=" + imgid +
                ", logid=" + logid +
                ", imgpath='" + imgpath + '\'' +
                '}';
    }
}
