package com.ypp.itproject.service;

import com.ypp.itproject.vo.*;

import java.util.List;

public interface IPostService {
    boolean verifyAuthor(String cid, int uid);

    PostVo getPost(String cid, boolean getDraft);
    List<PostPreviewVo> getTopPosts(int uid);
    List<PostPreviewVo> getAllPosts(int uid);
    List<PostPreviewVo> getAllDrafts(int uid);

    String createPost(int uid, NewPostVo vo);
    boolean deletePost(String cid);

    boolean updatePostInfo(String cid, int uid, UpdatePostInfoVo vo);
    boolean updateBlogContent(String cid, UpdateBlogContentVo vo);
    boolean updateFileContent(String cid, UpdateFileContentVo vo);

}
