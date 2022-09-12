package com.bookkeeping.backend.controller;

import com.bookkeeping.backend.entity.Cost;
import com.bookkeeping.backend.service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cost")
public class CostController {
    @Autowired
    CostService costService;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addCost(@RequestBody Cost cost) {
        try {
            this.costService.addCost(cost);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
