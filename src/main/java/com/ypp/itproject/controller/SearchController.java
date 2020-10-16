package com.ypp.itproject.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ypp.itproject.entity.Collection;
import com.ypp.itproject.exception.RestException;
import com.ypp.itproject.service.ICollectionService;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  Search collections by name and tags
 * </p>
 *
 * @author: ethan
 * @since 2020-09-23
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    private final ICollectionService collectionService;

    public SearchController(ICollectionService service) {
        this.collectionService = service;
    }

    @GetMapping(value = "/{uid}/all")
    public List<Collection> getAll(@PathVariable("uid") int uid){
        /*
            This method will be used for search all collections belongs to uid, that isDraft field set to false
            Return a List of Collection
         */
        Collection allCollection = new Collection();
        allCollection.setUid(uid);
        allCollection.setIsDraft(false);//is not a draft
        QueryWrapper<Collection> queryWrapper = new QueryWrapper<Collection>(allCollection);
        List resultList=collectionService.listObjs(queryWrapper);
        return resultList;
    }

    @GetMapping(value = "/{uid}")
    public List<Collection> searchTitle(@PathVariable("uid") int uid, @RequestParam String title,
                                        @RequestParam List<String> tags) {
        /*
            This method will be used for search collection title, tags
            Case sensitive
         */
        List<Collection> allCollection = this.getAll(uid);
        System.out.println(tags);
        int len=allCollection.size();
        for(int i=0;i<len;i++){
            Collection curr = allCollection.get(i);
            if(!curr.getTitle().contains(title)){
                allCollection.remove(i);
                i--;
                len--;
                continue;
            }
            //TODO: title or tags, contain one of them can be treated as passed
            if(tags!=null){
                JSONArray jsonString = new JSONArray( curr.getTag());
                List<Object> currTags = jsonString.toList();//all tags for curr collection
                System.out.println(currTags);
                boolean flag = false;
                for(int j=0;j<currTags.size();j++){
                    System.out.println(currTags.get(j));
                    if(tags.contains((String)currTags.get(j))){
                        flag=true;
                        break;
                    }
                }
                if(!flag){
                    //curr collection doesn't contain searched tags
                    allCollection.remove(i);
                    i--;
                    len--;
                    continue;
                }
            }
        }
        return allCollection;
    }


}
