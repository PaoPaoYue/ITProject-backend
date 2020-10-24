package com.ypp.itproject.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ypp.itproject.entity.Collection;
import com.ypp.itproject.util.LocalDateTimeConverter;

import java.time.LocalDateTime;

public class PostPreviewVo {

    private String cid;

    private Integer uid;

    private String title;

    private String description;

    private ContentEnum collectionType;

    @JsonSerialize(using = LocalDateTimeConverter.class)
    private LocalDateTime createTime;

    private String tag;

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
        this.createTime = collection.getCreateTime();
        this.tag = collection.getTag();
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

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
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
                ", collectionType='" + collectionType + '\'' +
                ", createTime=" + createTime +
                ", tag='" + tag + '\'' +
                ", coverImg='" + coverImg + '\'' +
                ", view=" + view +
                '}';
    }
}
