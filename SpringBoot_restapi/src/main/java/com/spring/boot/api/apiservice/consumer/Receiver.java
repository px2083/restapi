package com.spring.boot.api.apiservice.consumer;

import java.util.concurrent.CountDownLatch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.boot.api.apiservice.model.Crudtest;
import com.spring.boot.api.apiservice.service.RestApiService;

@Component
public class Receiver {

	private static Logger log = LogManager.getLogger(Receiver.class);
	
	@Autowired
	private RestApiService restApiService;
	
	private CountDownLatch latch = new CountDownLatch(5);
	
	public CountDownLatch getLatch() {
		return latch;
	}
	
	/**
	 * json to vo
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public Crudtest jsonToCrudtestVo(String json) throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, Crudtest.class);
	}
	
	@KafkaListener(topics = "test", groupId = "group-id")
    public void receiveTopic1(String payload) {
        log.info("Receiver payload: '{}'", payload);
        latch.countDown();
    }

	@KafkaListener(topics = "testCreate", groupId = "group-id")
	public void receiveTopicCreate(String payload) throws Exception {
		log.info("Receiver payload: '{}'", payload);
		
		Crudtest crudtest = jsonToCrudtestVo(payload);
		restApiService.insert(crudtest);
		
		latch.countDown();
	}

	@KafkaListener(topics = "testRead", groupId = "group-id")
	public void receiveTopicRead(String payload) throws Exception {
		log.info("Receiver payload: '{}'", payload);
		
		Crudtest crudtest = jsonToCrudtestVo(payload);
		restApiService.insert(crudtest);
		
		restApiService.selectByUsername(crudtest);
		latch.countDown();
	}

	@KafkaListener(topics = "testUpdate", groupId = "group-id")
	public void receiveTopicUpdate(String payload) throws Exception {
		log.info("Receiver payload: '{}'", payload);
		
		Crudtest crudtest = jsonToCrudtestVo(payload);
		restApiService.insert(crudtest);
		
		restApiService.update(crudtest);
		latch.countDown();
	}

	@KafkaListener(topics = "testDelete", groupId = "group-id")
	public void receiveTopicDelete(String payload) throws Exception {
		log.info("Receiver payload: '{}'", payload);
		
		Crudtest crudtest = jsonToCrudtestVo(payload);
		restApiService.insert(crudtest);
		
		restApiService.delete(crudtest);
		latch.countDown();
	}
}
