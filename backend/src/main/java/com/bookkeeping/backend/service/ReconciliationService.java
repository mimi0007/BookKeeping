package com.bookkeeping.backend.service;

import com.bookkeeping.backend.entity.MonthInfoAccess;
import com.bookkeeping.backend.entity.ReconciliationCost;
import com.bookkeeping.backend.entity.ReconciliationIncome;

public interface ReconciliationService {
    void addReconciliationIncome(ReconciliationIncome reconciliationIncome);

    void addReconciliationCost(ReconciliationCost reconciliationCost);

    void updateReconciliationIncome(MonthInfoAccess monthInfoAccess);

    void updateReconciliationCost(MonthInfoAccess monthInfoAccess);
}
