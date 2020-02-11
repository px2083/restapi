package com.spring.boot.api.apiservice.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    private static final Logger log = LogManager.getLogger(ScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, String payload) {
        kafkaTemplate.send(topic, payload);
        log.info("Message: " + payload + " sent to topic: " + topic);
        System.out.println("Message: " + payload + " sent to topic: " + topic);
    }


    @Scheduled(fixedRate = 1000)
    public void reportCurrentTime() {
        System.out.println("helloworld " + dateFormat.format(new Date()));
        log.info("helloworld " + dateFormat.format(new Date()));
        // send("test", "helloworld " + dateFormat.format(new Date()));
    }

    @KafkaListener(topics = "test")
    public void receiveTopic1(ConsumerRecord consumerRecord) {
        log.info("Receiver on topic1: "+consumerRecord.toString());
        System.out.println("Receiver on topic1: "+consumerRecord.toString());
    }
}