package com.bookkeeping.backend.dao;

import com.bookkeeping.backend.entity.ReconciliationCost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReconciliationCostDao extends JpaRepository<ReconciliationCost, Integer> {
    List<ReconciliationCost> findAllCostByYear(Integer year);
    ReconciliationCost findReconciliationIncomeByYearAndType(Integer year, String type);
}
