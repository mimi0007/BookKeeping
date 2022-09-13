package com.bookkeeping.backend.service;

import com.bookkeeping.backend.entity.MonthInfoAccess;
import com.bookkeeping.backend.entity.ReconciliationExpense;
import com.bookkeeping.backend.entity.ReconciliationIncome;

public interface ReconciliationService {
    void addReconciliationIncome(ReconciliationIncome reconciliationIncome);

    void addReconciliationExpense(ReconciliationExpense reconciliationExpense);

    void updateReconciliationIncome(MonthInfoAccess monthInfoAccess) throws NoSuchFieldException;

    void updateReconciliationExpense(MonthInfoAccess monthInfoAccess);
}
