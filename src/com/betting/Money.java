package com.betting;

public class Money {
    private int cent;

    public Money(int cent) {
        this.cent = cent;
    }

    public Money(double euro){
        this.cent = (int)(euro*100.0);
    }

    public int getCent() {
        return cent;
    }

    public void setCent(int cent) {
        this.cent = cent;
    }

    public double getEuro(){
        return (double)cent/100;
    }

    public void setEuro(double euro){
        this.cent = (int)(euro*100.0);
    }

    @Override
    public String toString() {
        double euro = (double)cent/100;
        return "" + euro + "€";
    }
}
