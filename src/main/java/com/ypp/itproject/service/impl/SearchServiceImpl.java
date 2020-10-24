package com.ypp.itproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypp.itproject.entity.Collection;
import com.ypp.itproject.entity.User;
import com.ypp.itproject.service.ICollectionService;
import com.ypp.itproject.service.ISearchService;
import com.ypp.itproject.service.IUserService;
import com.ypp.itproject.vo.AccountVo;
import com.ypp.itproject.vo.PostPreviewVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceImpl implements ISearchService {

    private final ICollectionService collectionService;
    private final IUserService userService;

    public SearchServiceImpl(ICollectionService service, IUserService userService) {
        this.collectionService = service;
        this.userService = userService;
    }

    @Override
    public List<AccountVo> searchUsers(String name) {
        if (name == null || name.isEmpty()) return new ArrayList<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like( "display_name", name);
        List<User> allMatchedUsers = userService.list(queryWrapper);
        return allMatchedUsers.stream().map(AccountVo::new).collect(Collectors.toList());
    }

    @Override
    public List<PostPreviewVo> searchUserPosts(int uid, String title, String category, String tag) {
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", uid).eq("is_draft", false);
        if (title != null && !title.isEmpty()) queryWrapper.like("title", title);
        if (category != null && !category.isEmpty()) queryWrapper.like("collection_type", category);
        List<Collection> allMatchedPosts = collectionService.list(queryWrapper);
        if (tag != null && !tag.isEmpty()) {
            allMatchedPosts = allMatchedPosts.stream()
                    .filter(x -> Arrays.asList(x.getTag().split(",")).contains(tag))
                    .collect(Collectors.toList());
        }
        return allMatchedPosts.stream().map(PostPreviewVo::new).collect(Collectors.toList());
    }
}
