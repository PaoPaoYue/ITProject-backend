package com.ypp.itproject.controller;

import com.ypp.itproject.jwt.JwtUtil;
import com.ypp.itproject.jwt.annotation.CheckLogin;
import com.ypp.itproject.jwt.exception.CheckPermissionException;
import com.ypp.itproject.service.IPostService;
import com.ypp.itproject.vo.*;
import com.ypp.itproject.vo.auth.UserAuthVo;
import com.ypp.itproject.vo.util.SuccessWapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>
 *  Post Article API
 *      CRUD apis for articles
 * </p>
 *
 * @author: ypp
 * @since 2020-10-23
 */
@Validated
@RestController
@RequestMapping("/post")
public class PostController {

    private final IPostService service;

    public PostController(IPostService service) {
        this.service = service;
    }

    /**
        Get a published post by cid
     */
    @GetMapping(value = "/{cid}")
    public PostVo getPost(@PathVariable("cid") @Size(min=32, max=32) String cid) {
        return service.getPost(cid, false);
    }

    /**
        Get all published posts by uid
     */
    @GetMapping(value = "/all/{uid}")
    public List<PostPreviewVo> getAllPosts(@PathVariable @Min(1) int uid) {
        return service.getAllPosts(uid);
    }

    /**
        Get top 5 posts of by uid
     */
    @GetMapping(value = "/top/{uid}")
    public List<PostPreviewVo> getTopPosts(@PathVariable @Min(1) int uid) {
        return service.getTopPosts(uid);
    }

    /**
        Get all drafted posts by uid
     */
    @CheckLogin
    @GetMapping(value = "/draft/{uid}")
    public List<PostPreviewVo> getDrafts(@PathVariable @Min(1) int uid) {
        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        if(userAuthVo.getUid() != uid) throw new CheckPermissionException();

        return service.getAllDrafts(uid);
    }

    /**
        Create a new draft and return the cid
     */
    @CheckLogin
    @PostMapping(value = "/new")
    public Map<String, String> createPost(@RequestBody @Valid NewPostVo vo) {
        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        int uid = userAuthVo.getUid();

        String cid = service.createPost(uid, vo);

        Map<String, String> res = new TreeMap<>();
        res.put("cid", cid);
        return res;
    }

    /**
        Delete a post by cid
     */
    @CheckLogin
    @PostMapping(value = "/delete/{cid}")
    public SuccessWapper deletePost(@PathVariable("cid") @Size(min=32, max=32) String cid) {
        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        int uid = userAuthVo.getUid();

        if (!service.verifyAuthor(cid, uid)) throw new CheckPermissionException();

        return new SuccessWapper(service.deletePost(cid));
    }

    /**
        Get all info of a post (published or drafted) by cid
     */
    @CheckLogin
    @GetMapping(value = "/edit/{cid}")
    public PostVo editPost(@PathVariable("cid") @Size(min=32, max=32) String cid) {
        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        int uid = userAuthVo.getUid();

        if (!service.verifyAuthor(cid, uid)) throw new CheckPermissionException();

        return service.getPost(cid, true);
    }

    /**
        Update post meta info
     */
    @CheckLogin
    @PostMapping(value = "/update/info/{cid}")
    public SuccessWapper updatePostInfo(@PathVariable("cid") @Size(min=32, max=32) String cid,
                                        @RequestBody @Valid UpdatePostInfoVo vo) {
        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        int uid = userAuthVo.getUid();

        if (!service.verifyAuthor(cid, uid)) throw new CheckPermissionException();

        return new SuccessWapper(service.updatePostInfo(cid, uid, vo));
    }

    /**
        Update blog content post
     */
    @CheckLogin
    @PostMapping(value = "/update/blog/{cid}")
    public SuccessWapper updateBlogContent(@PathVariable("cid") @Size(min=32, max=32) String cid,
                                           @RequestBody @Valid UpdateBlogContentVo vo) {
        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        int uid = userAuthVo.getUid();

        if (!service.verifyAuthor(cid, uid)) throw new CheckPermissionException();

        return new SuccessWapper(service.updateBlogContent(cid, vo));
    }

    /**
        Update pdf content post
     */
    @CheckLogin
    @PostMapping(value = "/update/pdf/{cid}")
    public SuccessWapper updatePdfContent(@PathVariable("cid") @Size(min=32, max=32) String cid,
                                          @RequestBody @Valid UpdateFileContentVo vo) {
        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        int uid = userAuthVo.getUid();

        if (!service.verifyAuthor(cid, uid)) throw new CheckPermissionException();

        return new SuccessWapper(service.updateFileContent(cid, vo));
    }
}
