package com.spring.boot.api.apiservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.api.apiservice.model.Crudtest;

@RestController
@RequestMapping(value = "kafka")
public class KafkaSendContorller {

	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
	@GetMapping("/send")
	public void send(@RequestParam Map<String, String> paramMap) throws Exception {
		kafkaTemplate.send(paramMap.get("topic"), paramMap.get("payload"));
	}
	
	@GetMapping("/insertTest")
	public void insertTest(Crudtest crudtest) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String crudtestJson = objectMapper.writeValueAsString(crudtest);
		kafkaTemplate.send("testCreate", crudtestJson);
	}
	
	@GetMapping("/selectTest")
	public void selectTest(Crudtest crudtest) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String crudtestJson = objectMapper.writeValueAsString(crudtest);
		kafkaTemplate.send("testRead", crudtestJson);
	}

	@GetMapping("/updateTest")
	public void updateTest(Crudtest crudtest) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String crudtestJson = objectMapper.writeValueAsString(crudtest);
		kafkaTemplate.send("testUpdate", crudtestJson);
	}
	
	@GetMapping("/deleteTest")
	public void deleteTest(Crudtest crudtest) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String crudtestJson = objectMapper.writeValueAsString(crudtest);
		kafkaTemplate.send("testDelete", crudtestJson);
	}
}
