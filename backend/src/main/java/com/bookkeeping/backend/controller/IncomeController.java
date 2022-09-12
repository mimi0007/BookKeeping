package com.bookkeeping.backend.controller;

import com.bookkeeping.backend.entity.Income;
import com.bookkeeping.backend.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/income")
public class IncomeController {
    @Autowired
    public IncomeService incomeService;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addIncome(@RequestBody Income income) {
        try {
            this.incomeService.addIncome(income);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
