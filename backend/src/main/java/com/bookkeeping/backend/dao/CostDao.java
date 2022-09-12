package com.bookkeeping.backend.dao;

import com.bookkeeping.backend.entity.Cost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CostDao extends JpaRepository<Cost, Integer> {

    @Query("SELECT year FROM Cost cost")
    List<Integer> findYears();
}
