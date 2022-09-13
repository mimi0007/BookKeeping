package com.bookkeeping.backend.dao;

import com.bookkeeping.backend.entity.ReconciliationExpense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReconciliationExpenseDao extends JpaRepository<ReconciliationExpense, Integer> {
    List<ReconciliationExpense> findAllExpenseByYear(Integer year);

    ReconciliationExpense findReconciliationExpenseByYearAndType(Integer year, String type);
}
