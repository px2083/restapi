package com.spring.boot.api.apiservice.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "kafka")
public class KafkaSendContorller {

	@Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
	
	@GetMapping("/send")
	public void send(@RequestParam Map<String, String> paramMap) throws Exception {
		kafkaTemplate.send(paramMap.get("topic"), paramMap.get("payload"));
	}
	
}
