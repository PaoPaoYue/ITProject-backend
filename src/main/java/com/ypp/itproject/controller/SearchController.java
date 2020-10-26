package com.ypp.itproject.controller;

import com.ypp.itproject.service.ISearchService;
import com.ypp.itproject.vo.AccountVo;
import com.ypp.itproject.vo.PostPreviewVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 *  Search API
 *      Search Articles for specific user based on
 *      tag,title,category
 *      Search Users based on display_name
 * </p>
 *
 * @author: ypp ethan
 * @since 2020-09-23
 */
@Validated
@RestController
@RequestMapping("/search")
public class SearchController {

    private final ISearchService searchService;

    public SearchController(ISearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping(value = "/user")
    public List<AccountVo> searchUser(@RequestParam @NotBlank String name) {
        /*
            This method will be used for search all users that contain the string name
            Case sensitive
         */
        return searchService.searchUsers(name);
    }


    @GetMapping(value = "/post/{uid}")
    public List<PostPreviewVo> searchPosts(@PathVariable("uid") @Min(1) int uid,
                                           @RequestParam(required = false) String title,
                                           @RequestParam(required = false) String category,
                                           @RequestParam(required = false) String tag){
        /*
            This method is used for search API with single params:
                title
                tag
                category
         */
        return searchService.searchUserPosts(uid, title, category, tag);
    }

}
