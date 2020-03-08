package com.spring.boot.api.mongo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.spring.boot.api.mongo.model.LogDoc;

@Service
public class LogService {
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public LogDoc insertLog(LogDoc logDoc) {
		return mongoTemplate.insert(logDoc);
	}
}
