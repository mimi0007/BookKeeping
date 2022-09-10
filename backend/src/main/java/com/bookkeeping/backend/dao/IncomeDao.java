package com.bookkeeping.backend.dao;

import com.bookkeeping.backend.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeDao extends JpaRepository<Income, Integer>{

}
