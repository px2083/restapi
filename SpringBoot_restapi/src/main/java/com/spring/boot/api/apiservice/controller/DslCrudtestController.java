package com.spring.boot.api.apiservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.api.apiservice.model.Crudtest;
import com.spring.boot.api.apiservice.service.QueryDSLService;

@RestController
@RequestMapping("/dsl")
public class DslCrudtestController {
	
	@Autowired
	private QueryDSLService queryDSLService;
	
	@GetMapping("/selectList")
	public List<Crudtest> selectList(Crudtest crudtest) {
		return queryDSLService.selectByUsername(crudtest);
	}

	@GetMapping("/selectone")
	public Optional<Crudtest> selectOne(Crudtest crudtest) {
		return queryDSLService.selectOneById(crudtest);
	}
	
}
