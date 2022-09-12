package com.bookkeeping.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "months")
public class Months {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer jan;
    private Integer feb;
    private Integer mar;
    private Integer apr;
    private Integer may;
    private Integer jun;
    private Integer jul;
    private Integer aug;
    private Integer sep;
    private Integer oct;
    private Integer nov;
    private Integer dec;

    public Integer getMonthValue(String month) {
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

    public void setMonthValue(String monthName, Integer monthValue) {
        switch (monthName) {
            case "jan":
                setJan(monthValue);
                break;
            case "feb":
                setFeb(monthValue);
                break;
            case "mar":
                setMar(monthValue);
                break;
            case "apr":
                setApr(monthValue);
                break;
            case "may":
                setMay(monthValue);
                break;
            case "jun":
                setJun(monthValue);
                break;
            case "jul":
                setJul(monthValue);
                break;
            case "aug":
                setAug(monthValue);
                break;
            case "sep":
                setSep(monthValue);
                break;
            case "oct":
                setOct(monthValue);
                break;
            case "nov":
                setNov(monthValue);
                break;
            default:
                setDec(monthValue);
                break;
        }
    }
}
