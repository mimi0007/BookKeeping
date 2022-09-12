package com.bookkeeping.backend.dao;

import com.bookkeeping.backend.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeDao extends JpaRepository<Income, Integer>{
    @Query("SELECT year FROM Income income")
    List<Integer> findYears();
}
