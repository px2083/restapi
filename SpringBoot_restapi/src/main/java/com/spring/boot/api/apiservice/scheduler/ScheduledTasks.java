package com.spring.boot.api.apiservice.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

	private static Logger log = LogManager.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

	
	public void send(String topic, String payload) { 
		kafkaTemplate.send(topic, payload); 
		log.info("Message: " + payload + " sent to topic: " + topic); 
	}
	
	
	@Scheduled(fixedRate = 1000) 
	public void reportCurrentTime() {
		send("test", "helloworld " + dateFormat.format(new Date())); 
	}
	

	@KafkaListener(topics = "test", groupId = "group-id")
    public void receiveTopic1(String message) {
        log.info("Receiver on topic1: "+message);
    }
}