package com.spring.boot.api.apiservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.boot.api.apiservice.model.Crudtest;
import com.spring.boot.api.apiservice.model.Ordertest;
import com.spring.boot.api.apiservice.repository.CrudtestRepository;
import com.spring.boot.api.apiservice.repository.OrdertestRepository;

@Service
public class RestApiService {
	@Autowired
	private CrudtestRepository crudtestRepository;
	
	@Autowired
	private OrdertestRepository ordertestRepository;
	
	@Transactional(readOnly = false)
	public Crudtest insert(Crudtest crudtestRequest) {
		Crudtest crudtest = Crudtest.builder()
				.userid(crudtestRequest.getUserid())
				.username(crudtestRequest.getUsername()).build();
		crudtestRepository.save(crudtest);
		return crudtest;
	}
	
	@Transactional(readOnly = true)
	public List<Crudtest> selectByUsername(Crudtest crudtest) throws Exception {
		return crudtestRepository.findCrudtestByUsername(crudtest.getUsername());
	}
	
	@Transactional(readOnly = false)
	public Optional<Crudtest> update(Crudtest crudtest) throws Exception {
		Optional<Crudtest> resultCrudtest = crudtestRepository.findById(crudtest.getId());
		resultCrudtest.ifPresent(selectCrudtest -> {
			selectCrudtest.setUsername(crudtest.getUsername());
			crudtestRepository.save(selectCrudtest);
		});
		return resultCrudtest;
	}
	
	@Transactional(readOnly = false)
	public Optional<Crudtest> delete(Crudtest crudtest) throws Exception {
		Optional<Crudtest> resultCrudtest = crudtestRepository.findById(crudtest.getId());
		resultCrudtest.ifPresent(selectCrudtest -> {
			crudtestRepository.delete(selectCrudtest);
		});
		return resultCrudtest;
	}

	@Transactional(readOnly = false)
	public List<Ordertest> selectOrderList(Crudtest crudtest) throws Exception {
		Ordertest newOrder = Ordertest.builder().oname("order1").crudtest(crudtest).build();
		ordertestRepository.save(newOrder);
		List<Ordertest> resultOrdertest = ordertestRepository.findAll();
		return resultOrdertest;
	}
}
