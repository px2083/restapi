package com.spring.boot.api.apiservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.spring.boot.api.apiservice.model.Crudtest;

public interface CrudtestRepository extends JpaRepository<Crudtest, Long>,QuerydslPredicateExecutor<Crudtest> {
	List<Crudtest> findCrudtestByUsername(String username);
	Optional<Crudtest> findById(Long id);
}
