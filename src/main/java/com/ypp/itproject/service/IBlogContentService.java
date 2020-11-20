package com.ypp.itproject.service;

import com.ypp.itproject.entity.BlogContent;
import com.baomidou.mybatisplus.extension.service.IService;


public interface IBlogContentService extends IService<BlogContent> {
    boolean updateContent(BlogContent blogContent);
}
