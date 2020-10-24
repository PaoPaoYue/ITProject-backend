package com.ypp.itproject;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ypp.itproject.entity.Collection;
import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.mapper.CollectionMapper;
import com.ypp.itproject.service.ICollectionService;
import com.ypp.itproject.service.IUserService;
import com.ypp.itproject.vo.*;
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
	IUserService service;

	@Autowired
	ICollectionService collectionService;

	@Test
	void test() {
		throw new RestException(0, "233333");
	}

	@Test
	void loginSuccess() {
		LoginVo vo = new LoginVo();
		vo.setUsername("paopao");
		vo.setPassword("py970529");
		Assert.notNull(service.login(vo), "login failed");
	}

	@Test
	void duplicateRegister() {
		RegisterVo vo = new RegisterVo();
		vo.setUsername("paopao");
		vo.setEmail("846260131@qq.com");
		vo.setPassword("py970529");
		try {
			service.register(vo);
		} catch (RestException e) {
			Assert.state(e.getCode() == 1, "check duplicate failed");
			Assert.state(e.getMessage().equals("user already exist"), "duplicate message wrong");
		}
	}

	@Test
	void duplicatePassword() {
		PasswordVo vo = new PasswordVo();
		vo.setPassword("py970529");
		try {
			service.updatePassword(7, vo);
		} catch (RestException e) {
			Assert.state(e.getCode() == 1, "check duplicate failed");
			Assert.state(e.getMessage().equals("new password cannot be the same as the old one"),
					"duplicate message wrong");
		}
	}

	@Test
	void updateAccount() {
		AccountVo vo = new AccountVo();
		vo.setEmail("846260131@qq.com");
		vo.setDisplayName("PaoPaoYue");
		vo.setSimpleDescription("");
		vo.setAvatar("");
		vo.setLocation("unknown, unknown");
		vo.setPhone("");
		vo.setContactFacebook("");
		vo.setContactLinkedin("");
		vo.setContactGithub("");
		Assert.isTrue(service.updateAccount(7, vo), "update account failed");
	}

	@Test
	void updateAboutMe() {
		AboutMeVo vo = new AboutMeVo();
		vo.setEducation("[]");
		vo.setWork("[]");
		vo.setSkillset("{\"field\":[], \"tech\":[], \"other\":[]}");
		vo.setInterest("[]");
		vo.setAward("[]");
		Assert.isTrue(service.updateAboutMe(7, vo), "update about_me failed");
	}


}
