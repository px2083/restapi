package com.spring.boot.api.apiservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.spring.boot.api.apiservice.model.Ordertest;

public interface OrdertestRepository extends JpaRepository<Ordertest, Long>,QuerydslPredicateExecutor<Ordertest> {
	
}
