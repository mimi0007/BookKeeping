package com.bookkeeping.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "income")
public class Income {
    @Id
    private Integer year;
    private int jan;
    private int feb;
    private int mar;
    private int apr;
    private int may;
    private int jun;
    private int jul;
    private int aug;
    private int sep;
    private int oct;
    private int nov;
    private int dec;

    public Income() {
    }

    public Income(Integer year, int jan, int feb, int mar, int apr, int may, int jun, int jul, int aug, int sep, int oct,
                  int nov, int dec) {
        this.year = year;
        this.jan = jan;
        this.feb = feb;
        this.mar = mar;
        this.apr = apr;
        this.may = may;
        this.jun = jun;
        this.jul = jul;
        this.aug = aug;
        this.sep = sep;
        this.oct = oct;
        this.nov = nov;
        this.dec = dec;
    }

    public Integer getYear() {
        return year;
    }

    public int getJan() {
        return jan;
    }

    public int getFeb() {
        return feb;
    }

    public int getMar() {
        return mar;
    }

    public int getApr() {
        return apr;
    }

    public int getMay() {
        return may;
    }

    public int getJun() {
        return jun;
    }

    public int getJul() {
        return jul;
    }

    public int getAug() {
        return aug;
    }

    public int getSep() {
        return sep;
    }

    public int getOct() {
        return oct;
    }

    public int getNov() {
        return nov;
    }

    public int getDec() {
        return dec;
    }

    public int getMonthValue(String month) {
        switch (month) {
            case "jan":
                return jan;
            case "feb":
                return feb;
            case "mar":
                return mar;
            case "apr":
                return apr;
            case "may":
                return may;
            case "jun":
                return jun;
            case "jul":
                return jul;
            case "aug":
                return aug;
            case "sep":
                return sep;
            case "oct":
                return oct;
            case "nov":
                return nov;
            default:
                return dec;
        }
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setJan(int jan) {
        this.jan = jan;
    }

    public void setFeb(int feb) {
        this.feb = feb;
    }

    public void setMar(int mar) {
        this.mar = mar;
    }

    public void setApr(int apr) {
        this.apr = apr;
    }

    public void setMay(int may) {
        this.may = may;
    }

    public void setJun(int jun) {
        this.jun = jun;
    }

    public void setJul(int jul) {
        this.jul = jul;
    }

    public void setAug(int aug) {
        this.aug = aug;
    }

    public void setSep(int sep) {
        this.sep = sep;
    }

    public void setOct(int oct) {
        this.oct = oct;
    }

    public void setNov(int nov) {
        this.nov = nov;
    }

    public void setDec(int dec) {
        this.dec = dec;
    }

    @Override
    public String toString() {
        return "Income{" +
                "year=" + year +
                ", jan=" + jan +
                ", feb=" + feb +
                ", mar=" + mar +
                ", apr=" + apr +
                ", may=" + may +
                ", jun=" + jun +
                ", jul=" + jul +
                ", aug=" + aug +
                ", sep=" + sep +
                ", oct=" + oct +
                ", nov=" + nov +
                ", dec=" + dec +
                '}';
    }
}
