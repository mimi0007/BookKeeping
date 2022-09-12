package com.bookkeeping.backend.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MonthInfoAccess {
    private Integer year;
    private String type;
    private String monthName;
    private Integer monthValue;
}
