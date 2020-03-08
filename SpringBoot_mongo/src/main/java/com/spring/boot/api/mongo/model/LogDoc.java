package com.spring.boot.api.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document("db")
@Getter
@Setter
public class LogDoc {
	@Id
	private String _id;
	
	private String testlog;
}
