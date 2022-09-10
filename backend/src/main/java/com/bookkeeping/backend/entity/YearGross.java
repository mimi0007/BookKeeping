package com.bookkeeping.backend.entity;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class YearGross {
    private Income income;
    private Cost cost;
    private Map<String, Integer> cumulativeIncome;
    private Map<String, Integer> cumulativeCost;
    private Map<String, Integer> result;
}
