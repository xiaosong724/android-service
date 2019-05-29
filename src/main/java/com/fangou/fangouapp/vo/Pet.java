package com.fangou.fangouapp.vo;

public class Pet {
    private int id;
    private String mname;
    private String petname;
    private String petpath;
    private String petmodel;
    private int hp;
    private int akt;
    private int con;
    private int csc;
    private int power;
    private int wit;
    private int zing;
    private int arsk;
    private int helmet;
    private int armour;
    private int weapon;
    private int rmb;
    private String maxexp;
    private String exp;
    private int herolevel;
    private String signinday;
    private String sign;
    private int pknum;
    public Pet() {
    }

    public Pet(String mname, String petname, String petpath, String petmodel, int hp, int akt, int con, int csc, int power, int wit, int zing, int arsk, int helmet, int armour, int weapon, int rmb, String maxexp, String exp, int herolevel, String signinday, String sign, int pknum) {
        this.mname = mname;
        this.petname = petname;
        this.petpath = petpath;
        this.petmodel = petmodel;
        this.hp = hp;
        this.akt = akt;
        this.con = con;
        this.csc = csc;
        this.power = power;
        this.wit = wit;
        this.zing = zing;
        this.arsk = arsk;
        this.helmet = helmet;
        this.armour = armour;
        this.weapon = weapon;
        this.rmb = rmb;
        this.maxexp = maxexp;
        this.exp = exp;
        this.herolevel = herolevel;
        this.signinday = signinday;
        this.sign = sign;
        this.pknum = pknum;
    }


    public Pet(int id, String mname, String petname, String petpath, String petmodel, int hp, int akt, int con, int csc, int power, int wit, int zing, int arsk, int helmet, int armour, int weapon, int rmb, String maxexp, String exp, int herolevel, String signinday, String sign, int pknum) {
        this.id = id;
        this.mname = mname;
        this.petname = petname;
        this.petpath = petpath;
        this.petmodel = petmodel;
        this.hp = hp;
        this.akt = akt;
        this.con = con;
        this.csc = csc;
        this.power = power;
        this.wit = wit;
        this.zing = zing;
        this.arsk = arsk;
        this.helmet = helmet;
        this.armour = armour;
        this.weapon = weapon;
        this.rmb = rmb;
        this.maxexp = maxexp;
        this.exp = exp;
        this.herolevel = herolevel;
        this.signinday = signinday;
        this.sign = sign;
        this.pknum = pknum;
    }

    public String getPetpath() {
        return petpath;
    }

    public void setPetpath(String petpath) {
        this.petpath = petpath;
    }

    public String getPetmodel() {
        return petmodel;
    }

    public void setPetmodel(String petmodel) {
        this.petmodel = petmodel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getPetname() {
        return petname;
    }

    public int getPknum() {
        return pknum;
    }

    public void setPknum(int pknum) {
        this.pknum = pknum;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAkt() {
        return akt;
    }

    public void setAkt(int akt) {
        this.akt = akt;
    }

    public int getCon() {
        return con;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public int getcsc() {
        return csc;
    }

    public void setcsc(int csc) {
        this.csc = csc;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getWit() {
        return wit;
    }

    public void setWit(int wit) {
        this.wit = wit;
    }

    public int getZing() {
        return zing;
    }

    public void setZing(int zing) {
        this.zing = zing;
    }

    public int getArsk() {
        return arsk;
    }

    public void setArsk(int arsk) {
        this.arsk = arsk;
    }

    public int getHelmet() {
        return helmet;
    }

    public void setHelmet(int helmet) {
        this.helmet = helmet;
    }

    public int getArmour() {
        return armour;
    }

    public void setArmour(int armour) {
        this.armour = armour;
    }

    public int getWeapon() {
        return weapon;
    }

    public void setWeapon(int weapon) {
        this.weapon = weapon;
    }

    public int getRmb() {
        return rmb;
    }

    public void setRmb(int rmb) {
        this.rmb = rmb;
    }

    public String getMaxexp() {
        return maxexp;
    }

    public void setMaxexp(String maxexp) {
        this.maxexp = maxexp;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public int getHerolevel() {
        return herolevel;
    }

    public void setHerolevel(int herolevel) {
        this.herolevel = herolevel;
    }

    public String getSigninday() {
        return signinday;
    }

    public void setSigninday(String signinday) {
        this.signinday = signinday;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", mname='" + mname + '\'' +
                ", petname='" + petname + '\'' +
                ", petpath='" + petpath + '\'' +
                ", petmodel='" + petmodel + '\'' +
                ", hp=" + hp +
                ", akt=" + akt +
                ", con=" + con +
                ", csc=" + csc +
                ", power=" + power +
                ", wit=" + wit +
                ", zing=" + zing +
                ", arsk=" + arsk +
                ", helmet=" + helmet +
                ", armour=" + armour +
                ", weapon=" + weapon +
                ", rmb=" + rmb +
                ", maxexp='" + maxexp + '\'' +
                ", exp='" + exp + '\'' +
                ", herolevel=" + herolevel +
                ", signinday=" + signinday +
                ", sign=" + sign +
                ", pknum=" + pknum +
                '}';
    }
}
