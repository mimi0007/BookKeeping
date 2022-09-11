package com.bookkeeping.backend.controller;

import com.bookkeeping.backend.entity.ReconciliationCost;
import com.bookkeeping.backend.entity.ReconciliationIncome;
import com.bookkeeping.backend.service.ReconciliationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reconciliation")
public class ReconciliationController {
    @Autowired
    ReconciliationService reconciliationService;

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
}
