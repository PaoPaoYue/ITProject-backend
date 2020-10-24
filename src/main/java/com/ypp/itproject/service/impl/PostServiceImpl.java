package com.ypp.itproject.service.impl;

import com.ypp.itproject.entity.BlogContent;
import com.ypp.itproject.entity.Collection;
import com.ypp.itproject.entity.FileContent;
import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.service.*;
import com.ypp.itproject.vo.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements IPostService {

    private static final int TOP_LIMIT=5;

    private final ICollectionService collectionService;
    private final IBlogContentService blogContentService;
    private final IFileContentService fileContentService;
    private final IUserService userService;

    public PostServiceImpl(ICollectionService collectionService, IBlogContentService blogContentService,
                           IFileContentService fileContentService, IUserService userService) {
        this.collectionService = collectionService;
        this.blogContentService = blogContentService;
        this.fileContentService = fileContentService;
        this.userService = userService;
    }

    @Override
    public boolean verifyAuthor(String cid, int uid) {
        Collection collection = collectionService.getById(cid);
        return (collection != null) && (collection.getUid().equals(uid));
    }

    @Override
    public PostVo getPost(String cid) {
        Collection collection =  collectionService.getById(cid);
        if (collection == null) throw new RestException(1, "post not found");
//        if (collection.getDraft()) throw new RestException(2, "post not published");

        PostPreviewVo previewVo = new PostPreviewVo(collection);
        ContentVo contentVo;
        switch (previewVo.getCollectionType()) {
            case BLOG:
                BlogContent blogContent = blogContentService.getById(cid);
                if (blogContent == null) throw new RestException(3, "post content missing");
                contentVo = new BlogContentVo(blogContent);
                break;
            case PDF:
                FileContent fileContent = fileContentService.getById(cid);
                if (fileContent == null) throw new RestException(3, "post content missing");
                contentVo = new FileContentVo(fileContent);
                break;
            default:
                return null;
        }

        collectionService.updateView(cid);
        return new PostVo(previewVo, contentVo);
    }

    @Override
    public List<PostPreviewVo> getTopPosts(int uid) {
        return collectionService.getTops(uid, TOP_LIMIT).stream().map(PostPreviewVo::new).collect(Collectors.toList());
    }

    @Override
    public List<PostPreviewVo> getAllPosts(int uid) {
        return collectionService.getAll(uid).stream().map(PostPreviewVo::new).collect(Collectors.toList());
    }

    @Override
    public List<PostPreviewVo> getAllDrafts(int uid) {
        return collectionService.getDrafts(uid).stream().map(PostPreviewVo::new).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public String createPost(int uid, NewPostVo vo) {
        Collection collection = new Collection();
        collection.setUid(uid);
        collection.setDraft(true);
        collection.setTitle(vo.getTitle());
        collection.setCollectionType(vo.getCollectionType().toString());
        collectionService.save(collection);


        String cid = collection.getCid();

        switch (vo.getCollectionType()) {
            case BLOG:
                BlogContent blogContent = new BlogContent();
                blogContent.setCid(cid);
                blogContent.setText("");
                blogContentService.save(blogContent);
                break;
            case PDF:
                FileContent fileContent = new FileContent();
                fileContent.setCid(cid);
                fileContent.setFile("[]");
                fileContentService.save(fileContent);
                break;
        }

        return cid;
    }

    @Override
    public boolean deletePost(String cid) {
        return collectionService.removeById(cid);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public boolean updatePostInfo(String cid, int uid, UpdatePostInfoVo vo) {
        Collection collection = vo.toCollection();
        collection.setCid(cid);

        return userService.addTags(uid, vo.getTag())
            && collectionService.updateCollection(collection);
    }

    @Override
    public boolean updateBlogContent(String cid, UpdateBlogContentVo vo) {
        BlogContent blogContent = vo.toBlogContent();
        blogContent.setCid(cid);
        return blogContentService.updateContent(blogContent);
    }

    @Override
    public boolean updateFileContent(String cid, UpdateFileContentVo vo) {
        FileContent fileContent = vo.toFileContent();
        fileContent.setCid(cid);
        return fileContentService.updateContent(fileContent);
    }
}
