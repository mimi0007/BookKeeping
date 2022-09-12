package com.bookkeeping.backend.controller;

import com.bookkeeping.backend.entity.MonthInfoAccess;
import com.bookkeeping.backend.entity.ReconciliationCost;
import com.bookkeeping.backend.entity.ReconciliationIncome;
import com.bookkeeping.backend.service.ReconciliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reconciliation")
public class ReconciliationController {
    @Autowired
    public ReconciliationService reconciliationService;

    @PostMapping("/add-income")
    public ResponseEntity<HttpStatus> addReconciliationIncome(@RequestBody ReconciliationIncome reconciliationIncome) {
        try {
            this.reconciliationService.addReconciliationIncome(reconciliationIncome);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-cost")
    public ResponseEntity<HttpStatus> addReconciliationCost(@RequestBody ReconciliationCost reconciliationCost) {
        try {
            this.reconciliationService.addReconciliationCost(reconciliationCost);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-income")
    public ResponseEntity<HttpStatus> updateReconciliationIncome(@RequestBody MonthInfoAccess monthInfoAccess) {
        try {
            this.reconciliationService.updateReconciliationIncome(monthInfoAccess);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update-cost")
    public ResponseEntity<HttpStatus> updateReconciliationCost(@RequestBody MonthInfoAccess monthInfoAccess) {
        try {
            this.reconciliationService.updateReconciliationCost(monthInfoAccess);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
