package com.bookkeeping.backend.service.serviceImpl;

import com.bookkeeping.backend.dao.CostDao;
import com.bookkeeping.backend.dao.MonthsDao;
import com.bookkeeping.backend.entity.Cost;
import com.bookkeeping.backend.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostServiceImpl implements CostService {
    @Autowired
    CostDao costDao;

    @Autowired
    MonthsDao monthsDao;

    @Override
    public void addCost(Cost cost) {
        monthsDao.save(cost.getMonths());
        costDao.save(cost);
    }
}
