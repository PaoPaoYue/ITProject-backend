package com.ypp.itproject.controller;

import com.ypp.itproject.exception.RestException;
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

    @GetMapping(value = "/{cid}")
    public PostVo getPost(@PathVariable("cid") @Size(min=32, max=32) String cid) {
        PostVo vo = service.getPost(cid);
        if (vo == null) throw new RestException(1, "post not found");
        return vo;
    }

    @GetMapping(value = "/all/{uid}")
    public List<PostPreviewVo> getAllPosts(@PathVariable @Min(1) int uid) {
        return service.getAllPosts(uid);
    }

    @GetMapping(value = "/top/{uid}")
    public List<PostPreviewVo> getTopPosts(@PathVariable @Min(1) int uid) {
        return service.getTopPosts(uid);
    }

    @CheckLogin
    @GetMapping(value = "/draft")
    public List<PostPreviewVo> getDrafts() {
        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        int uid = userAuthVo.getUid();
        return service.getAllDrafts(uid);
    }

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

    @CheckLogin
    @PostMapping(value = "/delete/{cid}")
    public SuccessWapper deletePost(@PathVariable("cid") @Size(min=32, max=32) String cid) {
        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        int uid = userAuthVo.getUid();

        if (!service.verifyAuthor(cid, uid)) throw new CheckPermissionException();

        return new SuccessWapper(service.deletePost(cid));
    }

    @CheckLogin
    @PostMapping(value = "/update/info/{cid}")
    public SuccessWapper updatePostInfo(@PathVariable("cid") @Size(min=32, max=32) String cid,
                                        @RequestBody @Valid UpdatePostInfoVo vo) {
        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        int uid = userAuthVo.getUid();

        if (!service.verifyAuthor(cid, uid)) throw new CheckPermissionException();

        return new SuccessWapper(service.updatePostInfo(cid, uid, vo));
    }

    @CheckLogin
    @PostMapping(value = "/update/blog/{cid}")
    public SuccessWapper updateBlogContent(@PathVariable("cid") @Size(min=32, max=32) String cid,
                                           @RequestBody @Valid UpdateBlogContentVo vo) {
        UserAuthVo userAuthVo = (UserAuthVo) JwtUtil.extract();
        int uid = userAuthVo.getUid();

        if (!service.verifyAuthor(cid, uid)) throw new CheckPermissionException();

        return new SuccessWapper(service.updateBlogContent(cid, vo));
    }

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
