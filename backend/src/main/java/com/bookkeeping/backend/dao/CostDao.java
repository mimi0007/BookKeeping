package com.bookkeeping.backend.dao;

import com.bookkeeping.backend.entity.Cost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostDao extends JpaRepository<Cost, Integer> {

}
