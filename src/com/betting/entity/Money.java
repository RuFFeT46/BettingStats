package com.betting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="money")
public class Money {
    private int id;
    private int cent;

    public Money(int cent) {
        this.cent = cent;
    }

    public Money(double euro){
        this.cent = (int)(euro*100.0);
    }



    @Id
    @Column(name = "money_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "cent")
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
