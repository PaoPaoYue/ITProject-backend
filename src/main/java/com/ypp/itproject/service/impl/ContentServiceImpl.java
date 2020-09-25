package com.ypp.itproject.service.impl;

import com.ypp.itproject.entity.Content;
import com.ypp.itproject.mapper.ContentMapper;
import com.ypp.itproject.service.IContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ethan
 * @since 2020-09-22
 */
@Service
public class ContentServiceImpl extends ServiceImpl<ContentMapper, Content> implements IContentService {

}
