package com.spring.boot.api.apiservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * DataSoruce를 직접 설정하기 위해 DataSourceAutoConfiguration exclude처리
 * scheduler 처리를 위한 @EnableScheduling 선언
 * @author DraChacha
 *
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootRestapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestapiApplication.class, args);
	}

}
