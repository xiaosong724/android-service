package com.fangou.fangouapp.vo;

public class Skill {
    private int id;
    private int petid;
    private String skillname;
    private String skillproperty;
    private double harm;
    private  int probability;

    public Skill() {
    }

    public Skill(int id, int petid, String skillname, String skillproperty, double harm, int probability) {
        this.id = id;
        this.petid = petid;
        this.skillname = skillname;
        this.skillproperty = skillproperty;
        this.harm = harm;
        this.probability = probability;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPetid() {
        return petid;
    }

    public void setPetid(int petid) {
        this.petid = petid;
    }

    public String getSkillname() {
        return skillname;
    }

    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }

    public String getSkillproperty() {
        return skillproperty;
    }

    public void setSkillproperty(String skillproperty) {
        this.skillproperty = skillproperty;
    }

    public double getHarm() {
        return harm;
    }

    public void setHarm(double harm) {
        this.harm = harm;
    }

    public int getProbability() {
        return probability;
    }

    public void setProbability(int probability) {
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id=" + id +
                ", petid=" + petid +
                ", skillname='" + skillname + '\'' +
                ", skillproperty='" + skillproperty + '\'' +
                ", harm=" + harm +
                ", probability=" + probability +
                '}';
    }
}
