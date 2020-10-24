package com.ypp.itproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypp.itproject.entity.Collection;
import com.ypp.itproject.mapper.CollectionMapper;
import com.ypp.itproject.service.ICollectionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Collection of the contents 服务实现类
 * </p>
 *
 * @author ethan
 * @since 2020-10-16
 */
@Service
public class CollectionServiceImpl extends ServiceImpl<CollectionMapper, Collection> implements ICollectionService {

    private final CollectionMapper mapper;

    public CollectionServiceImpl(CollectionMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean updateCollection(Collection collection) {

        return this.updateById(collection);
    }

    @Override
    public boolean updateView(String cid) {
        return mapper.updateView(cid);
    }

    @Override
    public List<Collection> getAll(int uid) {
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).eq("is_draft", false);
        queryWrapper.orderByDesc("create_time");
        return mapper.selectList(queryWrapper);
    }

    @Override
    public List<Collection> getTops(int uid, int limit) {
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).eq("is_draft", false);
        queryWrapper.orderByDesc("view", "create_time");
        queryWrapper.last("limit "+limit);
        return mapper.selectList(queryWrapper);
    }

    @Override
    public List<Collection> getDrafts(int uid) {
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).eq("is_draft", true);
        queryWrapper.orderByDesc("create_time");
        return mapper.selectList(queryWrapper);
    }
}
