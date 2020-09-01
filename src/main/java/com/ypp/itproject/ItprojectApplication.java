package com.ypp.itproject;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ypp.itproject.mapper")
public class ItprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItprojectApplication.class, args);
	}

}
