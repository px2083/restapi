package com.spring.boot.api.apiservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restapi")
public class RestApiController {
	
	@GetMapping("/hello")
	public String HelloWorld() throws Exception {
		return "Hello World";
	}
}
