package com.bookkeeping.backend.controller;

import com.bookkeeping.backend.entity.ReconciliationYearGross;
import com.bookkeeping.backend.entity.YearGross;
import com.bookkeeping.backend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    public DashboardService dashboardService;

    @GetMapping("/common-income-cost/{year}")
    public ResponseEntity<YearGross> getGrossIncomeAndCost(@PathVariable Integer year) {
        YearGross yearGross = this.dashboardService.getYearGross(year);

        if (yearGross == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(yearGross, HttpStatus.OK);
        }
    }

    @GetMapping("/reconciliation-income-expense/{year}")
    public ResponseEntity<ReconciliationYearGross> getReconGrossIncomeAndExpense(@PathVariable Integer year) {
        ReconciliationYearGross reconciliationYearGross = this.dashboardService.getReconciliationYearGross(year);

        if (reconciliationYearGross == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(reconciliationYearGross, HttpStatus.OK);
        }
    }

    @GetMapping("/years")
    public List<Integer> getYears() {
        return this.dashboardService.getYears();
    }
}
