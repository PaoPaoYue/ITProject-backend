package com.ypp.itproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypp.itproject.entity.Collection;
import com.ypp.itproject.entity.User;
import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.service.ICollectionService;
import com.ypp.itproject.service.IUserService;
import com.ypp.itproject.vo.AccountVo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  Search API
 *      Search Articles for specific user based on
 *      tag,title,category
 *      Search Users based on display_name
 * </p>
 *
 * @author: ethan
 * @since 2020-09-23
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    private final ICollectionService collectionService;
    private final IUserService userService;

    public SearchController(ICollectionService service, IUserService userService) {
        this.collectionService = service;
        this.userService = userService;
    }

    @GetMapping(value = "/post/{uid}/all")
    public List<Collection> getAllPost(@PathVariable("uid") int uid){
        /*
            This method will be used for search all collections belongs to uid, that isDraft field set to false
            Return a List of Collection
         */
        if(uid<=0){
            // uid less and equal to zero
            throw new RestException(0, "uid must be larger than 0");
        }
        Collection allCollection = new Collection();
        allCollection.setUid(uid);
        allCollection.setIsDraft(false);//is not a draft
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<Collection>(allCollection);
        List resultList=collectionService.listObjs(queryWrapper);
        if(resultList.isEmpty()){
            //no result
            throw new RestException(1, "no posts for the user");
        }
        return resultList;
    }

    @GetMapping(value = "/post/{uid}")
    public List<Collection> searchPosts(@PathVariable("uid") int uid, @RequestParam(required = false) String title,
                                             @RequestParam(required = false) String tag, @RequestParam(required = false) String category){
        /*
            This method is used for search API with single params:
                title
                tag
                category
         */
        if(uid<=0){
            // uid less and equal to zero
            throw new RestException(0, "uid must be larger than 0");
        }
        if(title!=null && category==null && tag==null){
            return searchTitle(uid, title);
        }
        if(category!=null && title==null && tag==null){
            return searchCategory(uid, category);
        }
        if(tag!=null && title==null && category==null){
            return searchTag(uid, tag);
        }

        return getAllPost(uid);
    }

    @GetMapping(value = "/user")
    public List<AccountVo> searchUser(@RequestParam String name) {
        /*
            This method will be used for search all users that contain the string name
            Case sensitive
         */
        if(name.isEmpty()){
            //no result
            throw new RestException(0, "name is empty");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like( "display_name", name);
        List<User> allMatchedUsers = userService.list(queryWrapper);
        List<AccountVo> result = new ArrayList<>();
        for(int j=0;j<allMatchedUsers.size();j++){
            result.add(new AccountVo(allMatchedUsers.get(j)));
        }
        if(result.isEmpty()){
            //no result
            throw new RestException(1, "no user matched "+name);
        }
        return result;
    }

    public List<Collection> searchTitle(int uid, @RequestParam String title) {
        /*
            This method will be used for search collection title, tags
            Case insensitive
         */
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid",uid);
        queryWrapper.like( "title", title);
        List<Collection> allCollection = collectionService.list(queryWrapper);
        if(allCollection.isEmpty()){
            //no result
            throw new RestException(0, "no posts title contain:"+title+" for the user");
        }
        return allCollection;
    }



    public List<Collection> searchCategory(int uid, @RequestParam String category) {
        /*
            This method will be used for search collection title, tags
            Case sensitive
         */
        List<Collection> allCollection = this.getAllPost(uid);
        int len=allCollection.size();
        for(int i=0;i<len;i++){
            Collection curr = allCollection.get(i);
            if(!curr.getCollectionType().toLowerCase().contains(category.toLowerCase())){
                allCollection.remove(i);
                i--;
                len--;
                continue;
            }
        }
        if(allCollection.isEmpty()){
            //no result
            throw new RestException(0, "no posts with category:"+category+" for the user");
        }
        return allCollection;
    }


    public List<Collection> searchTag(int uid, @RequestParam String tag) {
        /*
            This method will be used for search collection title, tags
            Case sensitive
         */
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<>();
        queryWrapper.like( "tag", ("'"+tag+"'"));
        queryWrapper.eq("uid",uid);
        List<Collection> allCollection = collectionService.list(queryWrapper);
        if(allCollection.isEmpty()){
            //no result
            throw new RestException(0, "no posts with tag:"+tag+" for the user");
        }
        return allCollection;
    }




}
