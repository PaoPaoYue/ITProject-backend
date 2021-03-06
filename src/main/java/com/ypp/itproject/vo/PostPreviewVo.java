package com.ypp.itproject.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ypp.itproject.entity.Collection;
import com.ypp.itproject.util.LocalDateTimeConverter;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PostPreviewVo {

    private String cid;

    private Integer uid;

    private String title;

    private String description;

    private ContentEnum collectionType;

    private Boolean isDraft;

    @JsonSerialize(using = LocalDateTimeConverter.class)
    private LocalDateTime createTime;

    private Set<String> tag;

    private String coverImg;

    private Integer view;

    public PostPreviewVo() {
    }

    public PostPreviewVo(Collection collection) {
        this.cid = collection.getCid();
        this.uid = collection.getUid();
        this.title = collection.getTitle();
        this.description = collection.getDescription();
        this.collectionType = ContentEnum.valueOf(collection.getCollectionType());
        this.isDraft = collection.getIsDraft();
        this.createTime = collection.getCreateTime();
        if (collection.getTag().isEmpty())
            this.tag = new HashSet<>();
        else
            this.tag = Arrays.stream(collection.getTag().split(",")).collect(Collectors.toSet());
        this.coverImg = collection.getCoverImg();
        this.view = collection.getView();
    }


    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ContentEnum getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(ContentEnum collectionType) {
        this.collectionType = collectionType;
    }

    public Boolean getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(Boolean draft) {
        isDraft = draft;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public Set<String> getTag() {
        return tag;
    }

    public void setTag(Set<String> tag) {
        this.tag = tag;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    public Integer getView() {
        return view;
    }

    public void setView(Integer view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "PostPreviewVo{" +
                "cid='" + cid + '\'' +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", collectionType=" + collectionType +
                ", isDraft=" + isDraft +
                ", createTime=" + createTime +
                ", tag='" + tag + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", view=" + view +
                '}';
    }
}
