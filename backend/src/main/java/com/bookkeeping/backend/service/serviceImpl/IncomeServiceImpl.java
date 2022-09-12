package com.bookkeeping.backend.service.serviceImpl;

import com.bookkeeping.backend.dao.IncomeDao;
import com.bookkeeping.backend.dao.MonthsDao;
import com.bookkeeping.backend.entity.Income;
import com.bookkeeping.backend.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IncomeServiceImpl implements IncomeService {
    @Autowired
    IncomeDao incomeDao;

    @Autowired
    MonthsDao monthsDao;

    @Override
    public void addIncome(Income income) {
        monthsDao.save(income.getMonths());
        incomeDao.save(income);
    }
}
