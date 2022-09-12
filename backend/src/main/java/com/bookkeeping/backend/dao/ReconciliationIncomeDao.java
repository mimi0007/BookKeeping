package com.bookkeeping.backend.dao;

import com.bookkeeping.backend.entity.ReconciliationIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReconciliationIncomeDao extends JpaRepository<ReconciliationIncome, Integer> {
    List<ReconciliationIncome> findAllIncomeByYear(Integer year);

    ReconciliationIncome findReconciliationIncomeByYearAndType(Integer year, String type);
}
