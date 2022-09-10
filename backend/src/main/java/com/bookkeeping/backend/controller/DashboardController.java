package com.bookkeeping.backend.controller;

import com.bookkeeping.backend.entity.YearGross;
import com.bookkeeping.backend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DashboardController {
    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/common-income-cost/{year}")
    public YearGross getGrossIncomeAndCost(@PathVariable Integer year) {
        return this.dashboardService.getYearGross(year);
    }
}