package com.spring.boot.api.mongo.consumer;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.spring.boot.api.mongo.model.LogDoc;
import com.spring.boot.api.mongo.service.LogService;

@Component
public class Receiver {

	private CountDownLatch latch = new CountDownLatch(1);
	
	public CountDownLatch getLatch() {
	    return latch;
  }

	@Autowired
	private LogService logService;
	
	@KafkaListener(topics = "log", groupId = "group-id")
    public void receiveTopic1(String message) throws Exception {
		LogDoc logDoc = new LogDoc();
		logDoc.setTestlog(message);
		logService.insertLog(logDoc);
		latch.countDown();
    }
	
}
