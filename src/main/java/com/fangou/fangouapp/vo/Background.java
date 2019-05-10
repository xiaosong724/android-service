package com.fangou.fangouapp.vo;

public class Background {
    private int id;
    private String backname;
    private String smbackpath;
    private String backpath;
    private String yesback;
    private String hightback;

    public Background() {
    }

    public Background(String backname, String smbackpath, String backpath, String yesback,String hightback) {
        this.backname = backname;
        this.smbackpath = smbackpath;
        this.backpath = backpath;
        this.yesback = yesback;
        this.hightback = hightback;

    }

    public Background(int id, String backname, String smbackpath, String backpath, String yesback,String hightback) {
        this.id = id;
        this.backname = backname;
        this.smbackpath = smbackpath;
        this.backpath = backpath;
        this.yesback = yesback;
        this.hightback = hightback;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackname() {
        return backname;
    }

    public String getSmbackpath() {
        return smbackpath;
    }

    public String getHightback() {
        return hightback;
    }

    public void setHightback(String hightback) {
        this.hightback = hightback;
    }

    public void setSmbackpath(String smbackpath) {
        this.smbackpath = smbackpath;
    }

    public void setBackname(String backname) {
        this.backname = backname;
    }

    public String getBackpath() {
        return backpath;
    }

    public void setBackpath(String backpath) {
        this.backpath = backpath;
    }

    public String getYesback() {
        return yesback;
    }

    public void setYesback(String yesback) {
        this.yesback = yesback;
    }

    @Override
    public String toString() {
        return "Background{" +
                "id=" + id +
                ", backname='" + backname + '\'' +
                ", smbackpath='" + smbackpath + '\'' +
                ", backpath='" + backpath + '\'' +
                ", yesback='" + yesback + '\'' +
                ", hightback='" + hightback + '\'' +
                '}';
    }
}
