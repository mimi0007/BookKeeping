package com.bookkeeping.backend.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "cost")
public class Cost {
    @Id
    @Column(nullable = false)
    private Integer year;
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
}
