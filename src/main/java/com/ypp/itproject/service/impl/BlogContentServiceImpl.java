package com.ypp.itproject.service.impl;

import com.ypp.itproject.entity.BlogContent;
import com.ypp.itproject.mapper.BlogContentMapper;
import com.ypp.itproject.service.IBlogContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypp.itproject.service.IRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableScheduling
public class BlogContentServiceImpl extends ServiceImpl<BlogContentMapper, BlogContent> implements IBlogContentService {

    private final static int BATCH_SIZE = 1000;
    private final static int BATCH_SAVE_DELAY = 10000;
    private final static int BATCH_SAVE_RATE = 10000;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final IRedisService redisService;

    public BlogContentServiceImpl(IRedisService redisService) {
        this.redisService = redisService;
    }

    @Override
    public boolean updateContent(BlogContent blogContent) {
//        return this.updateById(blogContent);
        redisService.enqueue(blogContent);
        return true;
    }

    @Scheduled(fixedRate = BATCH_SAVE_RATE, initialDelay = BATCH_SAVE_DELAY)
    public void saveBatch() {
        this.BatchUpdateContent();
    }

    public void BatchUpdateContent() {
        List<BlogContent> batch = redisService.getBatch(BATCH_SIZE);
        if (!batch.isEmpty()) {
            logger.debug("=============== BATCH UPDATE BLOG ===============");
                this.updateBatchById(batch);
            logger.debug("=================================================");
        }
    }


}
