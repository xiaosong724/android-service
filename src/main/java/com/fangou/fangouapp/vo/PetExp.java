package com.fangou.fangouapp.vo;

public class PetExp {
    private int maxexp;
    private int exp;

    public PetExp() {
    }

    public PetExp(int maxexp, int exp) {
        this.maxexp = maxexp;
        this.exp = exp;
    }

    public int getMaxexp() {
        return maxexp;
    }

    public void setMaxexp(int maxexp) {
        this.maxexp = maxexp;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public String toString() {
        return "PetExp{" +
                "maxexp=" + maxexp +
                ", exp=" + exp +
                '}';
    }
}
