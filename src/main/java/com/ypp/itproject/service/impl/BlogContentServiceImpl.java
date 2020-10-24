package com.ypp.itproject.service.impl;

import com.ypp.itproject.entity.BlogContent;
import com.ypp.itproject.mapper.BlogContentMapper;
import com.ypp.itproject.service.IBlogContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ypp.itproject.vo.BlogContentVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ethan
 * @since 2020-10-16
 */
@Service
public class BlogContentServiceImpl extends ServiceImpl<BlogContentMapper, BlogContent> implements IBlogContentService {

    @Override
    public boolean updateContent(BlogContent blogContent) {
        return this.updateById(blogContent);
    }

}
