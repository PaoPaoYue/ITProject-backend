package com.ypp.itproject;

import com.ypp.itproject.entity.Student;
import com.ypp.itproject.jwt.config.JwtProperties;
import com.ypp.itproject.jwt.core.JwtManager;
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
	JwtManager manager;

	@Test
	void contextLoads() {
		Assert.notNull(manager, "get student failed");
		logger.info("success");
	}

	private static final String usernamePattern = "^\\w{3,20}$";
    private static final String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)\\w{8,20}$";

	@Test
	void playground() {
		logger.debug(String.valueOf("22222222".matches(passwordPattern)));
		logger.debug(String.valueOf("aaaaaaaa".matches(passwordPattern)));
		logger.debug(String.valueOf("2222222_".matches(passwordPattern)));
	}

}
