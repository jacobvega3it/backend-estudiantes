package com.tresit.coursefeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CourseFeignServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CourseFeignServiceApplication.class, args);
	}

}
