package com.bookkeeping.backend.service.serviceImpl;

import com.bookkeeping.backend.dao.MonthsDao;
import com.bookkeeping.backend.dao.ReconciliationCostDao;
import com.bookkeeping.backend.dao.ReconciliationIncomeDao;
import com.bookkeeping.backend.entity.ReconciliationCost;
import com.bookkeeping.backend.entity.ReconciliationIncome;
import com.bookkeeping.backend.service.ReconciliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReconciliationServiceImpl implements ReconciliationService {
    @Autowired
    ReconciliationIncomeDao reconciliationIncomeDao;

    @Autowired
    ReconciliationCostDao reconciliationCostDao;

    @Autowired
    MonthsDao monthsDao;

    @Override
    public void addReconciliationIncome(ReconciliationIncome reconciliationIncome) {
        monthsDao.save(reconciliationIncome.getMonths());
        reconciliationIncomeDao.save(reconciliationIncome);
    }

    @Override
    public void addReconciliationCost(ReconciliationCost reconciliationCost) {
        monthsDao.save(reconciliationCost.getMonths());
        reconciliationCostDao.save(reconciliationCost);
    }
}
