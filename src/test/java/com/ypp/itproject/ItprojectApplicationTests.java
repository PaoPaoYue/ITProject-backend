package com.ypp.itproject;

import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.service.IPostService;
import com.ypp.itproject.service.IRedisService;
import com.ypp.itproject.service.IUserService;
import com.ypp.itproject.service.impl.BlogContentServiceImpl;

import com.ypp.itproject.vo.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.Set;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

	static private final int uid = 7;

	static private String cid = null;

	static private final int total = 15;

	static private int passed = 0;

	private void logProgress(String testcase) {
		passed++;
		logger.info("tests completed: {}/{} - {}", passed, total, testcase);
	}

	@Test
	@Order(1)
	void loginSuccess() {
		LoginVo vo = new LoginVo();
		vo.setUsername("paopao");
		vo.setPassword("py970529");
		Assert.notNull(service.login(vo), "login failed");
		logProgress("loginSuccess");
	}

	@Test
	@Order(2)
	void unknownUser() {
		LoginVo vo = new LoginVo();
		vo.setUsername("paopaopaopao");
		vo.setPassword("py970529");
		try {
			service.login(vo);
		} catch (RestException e) {
			Assert.state(e.getCode() == 1, "check username failed");
			Assert.state(e.getMessage().equals("username not found"), "error message wrong");
		}
		logProgress("unknownUser");
	}

	@Test
	@Order(3)
	void wrongPassword() {
		LoginVo vo = new LoginVo();
		vo.setUsername("paopao");
		vo.setPassword("py970529999");
		try {
			service.login(vo);
		} catch (RestException e) {
			Assert.state(e.getCode() == 2, "check password failed");
			Assert.state(e.getMessage().equals("password incorrect"), "error message wrong");
		}
		logProgress("wrongPassword");
	}

	@Test
	@Order(4)
	void duplicateRegister() {
		RegisterVo vo = new RegisterVo();
		vo.setUsername("paopao");
		vo.setEmail("846260131@qq.com");
		vo.setPassword("py970529");
		try {
			service.register(vo);
		} catch (RestException e) {
			Assert.state(e.getCode() == 1, "check duplicate failed");
			Assert.state(e.getMessage().equals("user already exist"), "error message wrong");
			logProgress("duplicateRegister");
		}
	}

	@Test
	@Order(5)
	void duplicatePassword() {
		PasswordVo vo = new PasswordVo();
		vo.setPassword("py970529");
		try {
			service.updatePassword(7, vo);
		} catch (RestException e) {
			Assert.state(e.getCode() == 1, "check duplicate failed");
			Assert.state(e.getMessage().equals("new password cannot be the same as the old one"),
					"duplicate message wrong");
			logProgress("duplicatePassword");
		}
	}

	@Test
	@Order(6)
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
		logProgress("updateAccount");
	}

	@Test
	@Order(7)
	void updateAboutMe() {
		AboutMeVo vo = new AboutMeVo();
		vo.setEducation("[]");
		vo.setWork("[]");
		vo.setSkillset("{\"field\":[], \"tech\":[], \"other\":[]}");
		vo.setInterest("[]");
		vo.setAward("[]");
		Assert.isTrue(service.updateAboutMe(7, vo), "update about_me failed");
		logProgress("updateAboutMe");
	}

	@Test
	@Order(8)
	void addEmptyTags() {
		Set<String> tags = service.getTags(uid);
		Set<String> emptyTags = new HashSet<>();
		Assert.isTrue(service.addTags(uid, emptyTags), "add tag failed");
		Assert.isTrue(tags.equals(service.getTags(uid)), "tag not the same");
		logProgress("addEmptyTags");
	}

	@Test
	@Order(9)
	void createNewPost() {
		NewPostVo vo = new NewPostVo();
		vo.setCollectionType(ContentEnum.BLOG);
		vo.setTitle("test post");
		cid = postService.createPost(uid, vo);
		Assert.notNull(cid, "post not created");
		logProgress("createNewPost");
	}

	@Test
	@Order(10)
	void verifyOwnership() {
		Assert.isTrue(postService.verifyAuthor(cid, uid), "post ownership wrong - owner");
		Assert.isTrue(!postService.verifyAuthor(cid, uid+1), "post ownership wrong - wrong person");
		logProgress("verifyOwnership");
	}

	@Test
	@Order(11)
	void viewUnpublishedPost() {
		try {
			postService.getPost(cid, false);
		} catch (RestException e) {
			Assert.state(e.getCode() == 2, "drafted post visible to public");
			Assert.state(e.getMessage().equals("post not published"),
					"error message wrong");
		}
		Assert.notNull(postService.getPost(cid, true), "drafted post not visible to user");
		logProgress("viewUnpublishedPost");
	}

	@Test
	@Order(12)
	void updateBlogContent() throws InterruptedException {
		UpdateBlogContentVo vo = new UpdateBlogContentVo();
		vo.setText("test body");
		Assert.isTrue(postService.updateBlogContent(cid, vo), "update blog content failed");
		Thread.sleep(10000);
		logProgress("updateBlogContent");
	}

	@Test
	@Order(13)
	void changeVisibility() {
		UpdatePostInfoVo vo = new UpdatePostInfoVo();
		vo.setIsDraft(false);
		Assert.isTrue(postService.updatePostInfo(cid, uid, vo), "update blog info failed");
		logProgress("changeVisibility");
	}

	@Test
	@Order(14)
	void viewPublishedPost() {
		PostVo vo = postService.getPost(cid, false);
		Assert.notNull(vo, "published post not visible to public");
		Assert.isTrue(vo.getInfo().getTitle().equals("test post"), "wrong title");
		BlogContentVo content  = (BlogContentVo) vo.getContent();
		Assert.isTrue(content.getText().equals("test body"), "wrong content");
		logProgress("viewPublishedPost");
	}

	@Test
	@Order(15)
	void deleteNewPost() {
		Assert.isTrue(postService.deletePost(cid), "post not deleted");
		logProgress("deleteNewPost");
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
