package com.ypp.itproject.service;

import com.ypp.itproject.entity.BlogContent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypp.itproject.vo.BlogContentVo;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ethan
 * @since 2020-10-16
 */
public interface IBlogContentService extends IService<BlogContent> {
    boolean updateContent(BlogContent blogContent);
}
