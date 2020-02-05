package com.spring.boot.api.apiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@RequestMapping("apiservice/restapi")
class SpringBootRestapiApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestapiApplicationTests.class, args);
	}

	@GetMapping("/hello")
	public String HelloWorld() {
		return "Hello World";
	}

}
