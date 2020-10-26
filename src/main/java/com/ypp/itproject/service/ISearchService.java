package com.ypp.itproject.service;

import com.ypp.itproject.vo.AccountVo;
import com.ypp.itproject.vo.PostPreviewVo;

import java.util.List;

public interface ISearchService {

    List<AccountVo> searchUsers(String name);
    List<PostPreviewVo> searchUserPosts(int uid, String title, String category, String tag);
}
