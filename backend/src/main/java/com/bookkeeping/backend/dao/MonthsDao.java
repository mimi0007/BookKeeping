package com.bookkeeping.backend.dao;

import com.bookkeeping.backend.entity.Months;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonthsDao extends JpaRepository<Months, Integer> {
}
