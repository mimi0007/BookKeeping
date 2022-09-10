package com.bookkeeping.backend.service;

import com.bookkeeping.backend.entity.ReconciliationYearGross;
import com.bookkeeping.backend.entity.YearGross;


public interface DashboardService {
    YearGross getYearGross(Integer year);

    ReconciliationYearGross getReconciliationYearGross(Integer year);
}
