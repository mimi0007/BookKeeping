package com.bookkeeping.backend.service;

import com.bookkeeping.backend.entity.ReconciliationYearGross;
import com.bookkeeping.backend.entity.YearGross;

import java.util.List;


public interface DashboardService {
    YearGross getYearGross(Integer year);

    ReconciliationYearGross getReconciliationYearGross(Integer year);

    List<Integer> getYears();
}
