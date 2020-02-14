package com.spring.boot.api.apiservice.consumer;

import java.util.concurrent.CountDownLatch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

	private static Logger log = LogManager.getLogger(Receiver.class);
	
	private CountDownLatch latch = new CountDownLatch(1);
	
	public CountDownLatch getLatch() {
	    return latch;
	  }
	
	@KafkaListener(topics = "test", groupId = "group-id")
    public void receiveTopic1(String payload) {
        log.info("Receiver payload: '{}'", payload);
        latch.countDown();
    }
}
