package com.spring.boot.api.apiservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.api.apiservice.model.Crudtest;
import com.spring.boot.api.apiservice.model.Ordertest;
import com.spring.boot.api.apiservice.service.RestApiService;

@RestController
@RequestMapping("/crudtest")
public class CrudtestController {
	
	@Autowired
	private RestApiService restApiService;
	
	@GetMapping("/insertTest")
	public Crudtest insertTest(Crudtest crudtest) throws Exception {
		return restApiService.insert(crudtest);
	}
	
	@GetMapping("/selectTest")
	public List<Crudtest> selectTest(Crudtest crudtest) throws Exception {
		return restApiService.selectByUsername(crudtest);
	}

	@GetMapping("/selectOrder")
	public List<Ordertest> selectOrder(Crudtest crudtest) throws Exception {
		return restApiService.selectOrderList(crudtest);
	}

	@GetMapping("/updateTest")
	public Optional<Crudtest> updateTest(Crudtest crudtest) throws Exception {
		return restApiService.update(crudtest);
	}
	
	@GetMapping("/deleteTest")
	public Optional<Crudtest> deleteTest(Crudtest crudtest) throws Exception {
		return restApiService.delete(crudtest);
	}
	
}
