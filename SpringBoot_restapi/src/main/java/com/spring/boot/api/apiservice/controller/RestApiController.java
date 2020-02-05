package com.spring.boot.api.apiservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.api.apiservice.model.Crudtest;
import com.spring.boot.api.apiservice.service.RestApiService;

@RestController
@RequestMapping("/restapi")
public class RestApiController {

	@Autowired
	RestApiService restApiService;
	
	@GetMapping("/hello")
	public String HelloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/insertTest")
	public void insertTest(Crudtest crudtest) {
		restApiService.insert(crudtest);
	}
	
	@GetMapping("/selectTest")
	public int selectTest(Crudtest crudtest) {
		List<Crudtest> crudtestList = restApiService.selectByUsername(crudtest);
		return crudtestList.size();
		
	}

	@GetMapping("/updateTest")
	public void updateTest(Crudtest crudtest) {
		restApiService.update(crudtest);
	}
	
	@GetMapping("/deleteTest")
	public void deleteTest(Crudtest crudtest) {
		restApiService.delete(crudtest);
	}
}
