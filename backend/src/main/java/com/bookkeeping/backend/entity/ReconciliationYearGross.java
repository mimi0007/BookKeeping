package com.bookkeeping.backend.entity;


import lombok.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ReconciliationYearGross {
    private List<ReconciliationIncome> reconciliationIncome;
    private List<ReconciliationExpense> reconciliationExpense;
    private Map<String, Integer> reconciliationResult;
    private Map<String, Integer> finalResult;
    private Map<String, Integer> cumulativeFinalResult;
}
