package com.fangou.fangouapp.vo;

public class ImgidAndImgpath {
    private int imgid;
    private String imgpath;

    public ImgidAndImgpath() {
    }

    public ImgidAndImgpath(int imgid, String imgpath) {
        this.imgid = imgid;
        this.imgpath = imgpath;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    @Override
    public String toString() {
        return "ImgidAndImgpath{" +
                "imgid=" + imgid +
                ", imgpath='" + imgpath + '\'' +
                '}';
    }
}
