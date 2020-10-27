package com.ypp.itproject;

import com.ypp.itproject.entity.BlogContent;
import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.service.IPostService;
import com.ypp.itproject.service.IRedisService;
import com.ypp.itproject.service.IUserService;
import com.ypp.itproject.service.impl.BlogContentServiceImpl;

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
	IPostService postService;

	@Autowired
	BlogContentServiceImpl blogContentService;

	@Autowired
	IRedisService redisService;

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



// ************ Test Redis Improvement ************ //

	//	@Test
//	public void test1() throws InterruptedException {
//		ExecutorService executorService = Executors.newCachedThreadPool();
//        final CountDownLatch countDownLatch = new CountDownLatch(1000);
//
//		logger.info("=================== TEST 1 ===================");
//		logger.info("CONCURRENTLY INSERT 1000 RECORD - NO CACHING");
//
//		long begin = System.currentTimeMillis();
//
//		BlogContent blogContent = new BlogContent();
//		blogContent.setCid("f4d30681daa34a5e99e8bddbb1db5176");
//
//
//        for (int i = 0; i < 1000; i++) {
//            final int count = i;
//            executorService.execute(() -> {
//                try {
//                    blogContent.setText(Integer.toString(count));
//					blogContentService.updateById(blogContent);
//                } catch (Exception e) {
//                    // log.error("exception" , e);
//                }
//                countDownLatch.countDown();
//            });
//        }
//        countDownLatch.await();
//
//        long end = System.currentTimeMillis();
//		double cost = (double)(end - begin)/1000;
//
//		logger.info("Test Result: ");
//		logger.info("Time spend:  {} s", cost);
//		logger.info("Throughput:  {} /s", Math.round(1000/cost));
//		logger.info("================== TEST END ==================");
//
//        executorService.shutdown();
//	}
//
//	@Test
//	public void test2() throws InterruptedException {
//		ExecutorService executorService = Executors.newCachedThreadPool();
//        final CountDownLatch countDownLatch = new CountDownLatch(1000);
//
//		logger.info("=================== TEST 2 ===================");
//		logger.info("CONCURRENTLY INSERT 1000 RECORD - CACHING");
//
//		long begin = System.currentTimeMillis();
//
//		BlogContent blogContent = new BlogContent();
//		blogContent.setCid("f4d30681daa34a5e99e8bddbb1db5176");
//
//        for (int i = 0; i < 1000; i++) {
//            final int count = i;
//            executorService.execute(() -> {
//                try {
//                    blogContent.setText(Integer.toString(count));
//					blogContentService.updateContent(blogContent);
//                } catch (Exception e) {
//                    // log.error("exception" , e);
//                }
//                countDownLatch.countDown();
//            });
//        }
//        countDownLatch.await();
//
//        long end = System.currentTimeMillis();
//		double cost = (double)(end - begin)/1000;
//
//		logger.info("Test Result: ");
//		logger.info("Time spend:  {} s", cost);
//		logger.info("Throughput:  {} /s", Math.round(1000/cost));
//		logger.info("================== TEST END ==================");
//
//        executorService.shutdown();
//
//	}
//
//	@Test
//	public void test3()  {
//		logger.info("=================== TEST 3 ===================");
//		logger.info("SAVE 1000 BLOG CONTENT IN A BATCH");
//		logger.info("Test Result: ");
//		long begin = System.currentTimeMillis();
//
//		blogContentService.saveBatch();
//
//		long end = System.currentTimeMillis();
//		double cost = (double)(end - begin)/1000;
//
//		logger.info("Time spend:  {} s", cost);
//		logger.info("================== TEST END ==================");
//	}


}
