package com.fangou.fangouapp.vo;

public class Loveimg {
    private int imgid;
    private int logid;
    private String imgpath;
    private String filetype;

    public Loveimg() {
    }

    public Loveimg(int logid, String imgpath,String filetype) {
        this.logid = logid;
        this.imgpath = imgpath;
        this.filetype=filetype;
    }

    public Loveimg(int imgid, int logid, String imgpath,String filetype) {
        this.imgid = imgid;
        this.logid = logid;
        this.imgpath = imgpath;
        this.filetype=filetype;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
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
                ", filetype='" + filetype + '\'' +
                '}';
    }
}
