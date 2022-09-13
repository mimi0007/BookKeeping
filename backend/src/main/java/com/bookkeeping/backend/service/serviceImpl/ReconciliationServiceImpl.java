package com.bookkeeping.backend.service.serviceImpl;

import com.bookkeeping.backend.dao.MonthsDao;
import com.bookkeeping.backend.dao.ReconciliationExpenseDao;
import com.bookkeeping.backend.dao.ReconciliationIncomeDao;
import com.bookkeeping.backend.entity.MonthInfoAccess;
import com.bookkeeping.backend.entity.ReconciliationExpense;
import com.bookkeeping.backend.entity.ReconciliationIncome;
import com.bookkeeping.backend.service.ReconciliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static com.bookkeeping.backend.utility.Constants.MONTH_NAMES;

@Service
public class ReconciliationServiceImpl implements ReconciliationService {
    private final List<String> allMonths = Arrays.asList(MONTH_NAMES);
    @Autowired
    ReconciliationIncomeDao reconciliationIncomeDao;

    @Autowired
    ReconciliationExpenseDao reconciliationExpenseDao;

    @Autowired
    MonthsDao monthsDao;

    @Override
    public void addReconciliationIncome(ReconciliationIncome reconciliationIncome) {
        monthsDao.save(reconciliationIncome.getMonths());
        reconciliationIncomeDao.save(reconciliationIncome);
    }

    @Override
    public void addReconciliationExpense(ReconciliationExpense reconciliationExpense) {
        monthsDao.save(reconciliationExpense.getMonths());
        reconciliationExpenseDao.save(reconciliationExpense);
    }

    @Override
    public void updateReconciliationIncome(MonthInfoAccess monthInfoAccess) {
        Integer year = monthInfoAccess.getYear();
        Integer monthValue = monthInfoAccess.getMonthValue();
        String monthName = monthInfoAccess.getMonthName();
        String type = monthInfoAccess.getType();

        if (!allMonths.contains(monthName)) {
            throw new NoSuchElementException("Month Does not Exist");
        }

        ReconciliationIncome reconciliationIncome =
                reconciliationIncomeDao.findReconciliationIncomeByYearAndType(year, type);

        reconciliationIncome.getMonths().setMonthValue(monthName, monthValue);
        monthsDao.save(reconciliationIncome.getMonths());
    }

    @Override
    public void updateReconciliationExpense(MonthInfoAccess monthInfoAccess) {
        Integer year = monthInfoAccess.getYear();
        Integer monthValue = monthInfoAccess.getMonthValue();
        String monthName = monthInfoAccess.getMonthName();
        String type = monthInfoAccess.getType();

        if (!allMonths.contains(monthName)) {
            throw new NoSuchElementException("Month Does not Exist");
        }

        ReconciliationExpense reconciliationCost =
                reconciliationExpenseDao.findReconciliationExpenseByYearAndType(year, type);

        reconciliationCost.getMonths().setMonthValue(monthName, monthValue);
        monthsDao.save(reconciliationCost.getMonths());
    }

}
