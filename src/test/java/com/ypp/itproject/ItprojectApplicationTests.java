package com.ypp.itproject;

import com.ypp.itproject.entity.Student;
import com.ypp.itproject.service.IStudentService;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

@SpringBootTest
class ItprojectApplicationTests {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	ApplicationContext ioc;


	@Autowired
	IStudentService service;

	@Test
	void contextLoads() {
		Student student = service.getById(0);
		logger.info(student.toString());
		Assert.notNull(student, "get student failed");
		logger.info("success");
	}

}
