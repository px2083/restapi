package com.spring.boot.api.apiservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.api.apiservice.model.Crudtest;
import com.spring.boot.api.apiservice.repository.CrudtestRepository;

@Service
public class RestApiService {
	
	@Autowired
	CrudtestRepository crudtestRepository;
	
	public void insert(Crudtest crudtest) {
		crudtestRepository.save(crudtest);
	}
	
	public List<Crudtest> selectByUsername(Crudtest crudtest) {
		return crudtestRepository.findCrudtestByUsername(crudtest.getUsername());
	}
	
	public void update(Crudtest crudtest) {
		Optional<Crudtest> resultCrudtest = crudtestRepository.findById(crudtest.getId());
		resultCrudtest.ifPresent(selectCrudtest -> {
			selectCrudtest.setUsername(crudtest.getUsername());
			Crudtest newCrudtest = crudtestRepository.save(selectCrudtest);
			System.out.println("update: " + newCrudtest.toString());
		});
	}
	
	public void delete(Crudtest crudtest) {
		Optional<Crudtest> resultCrudtest = crudtestRepository.findById(crudtest.getId());
		resultCrudtest.ifPresent(selectCrudtest -> {
			crudtestRepository.delete(selectCrudtest);
		});
		
	}

}
