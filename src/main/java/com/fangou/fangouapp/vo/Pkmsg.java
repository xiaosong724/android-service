package com.fangou.fangouapp.vo;

public class Pkmsg {
    private String whoakt;//谁发动攻击
    private String beiakt;//被攻击
    private String skillname;//发动技能,攻击的名称
    //这次攻击的伤害,多种情况,技能加成,暴击加成,必杀技加成,或技能暴击双叠加,或必杀技暴击双叠加
    private int harm;
    private int hpreduceme;//当前剩余血量
    private int hpreduceto;//对方当前剩余血量
    private int csc;//是否暴击或必杀技,或必杀加暴击,触发值为"暴击....",反之为空;
    private String passivity;//对方是否触发了被动,触发值为被动名.反之为空;
    private int hpadd;//触发被动所加血量,防御力乘于技能百分比;
    private String win;//谁获得了胜利,血量小于0;

    public Pkmsg() {
    }

    public Pkmsg(String whoakt, String skillname, int harm, int hpreduceme, int hpreduceto, int csc, String passivity, int hpadd, String win) {
        this.whoakt = whoakt;
        this.skillname = skillname;
        this.harm = harm;
        this.hpreduceme = hpreduceme;
        this.hpreduceto = hpreduceto;
        this.csc = csc;
        this.passivity = passivity;
        this.hpadd = hpadd;
        this.win = win;
    }

    public String getWhoakt() {
        return whoakt;
    }

    public void setWhoakt(String whoakt) {
        this.whoakt = whoakt;
    }

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }

    public int getHarm() {
        return harm;
    }

    public void setHarm(int harm) {
        this.harm = harm;
    }

    public int getHpreduceme() {
        return hpreduceme;
    }

    public void setHpreduceme(int hpreduceme) {
        this.hpreduceme = hpreduceme;
    }

    public int getHpreduceto() {
        return hpreduceto;
    }

    public void setHpreduceto(int hpreduceto) {
        this.hpreduceto = hpreduceto;
    }

    public int getCsc() {
        return csc;
    }

    public void setCsc(int csc) {
        this.csc = csc;
    }

    public String getPassivity() {
        return passivity;
    }

    public void setPassivity(String passivity) {
        this.passivity = passivity;
    }

    public int getHpadd() {
        return hpadd;
    }

    public void setHpadd(int hpadd) {
        this.hpadd = hpadd;
    }

    public String getWin() {
        return win;
    }

    public void setWin(String win) {
        this.win = win;
    }

    @Override
    public String toString() {
        return "Pkmsg{" +
                "whoakt='" + whoakt + '\'' +
                ", skillname='" + skillname + '\'' +
                ", harm=" + harm +
                ", hpreduceme=" + hpreduceme +
                ", hpreduceto=" + hpreduceto +
                ", csc='" + csc + '\'' +
                ", passivity='" + passivity + '\'' +
                ", hpadd=" + hpadd +
                ", win='" + win + '\'' +
                '}';
    }
}
