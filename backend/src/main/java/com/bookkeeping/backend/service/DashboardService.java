package com.bookkeeping.backend.service;

import com.bookkeeping.backend.entity.YearGross;


public interface DashboardService {
    public YearGross getYearGross(Integer year);
}
