package com.ypp.itproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * Collection of the contents
 * </p>
 *
 * @author ypp
 * @since 2020-10-23
 */
public class Collection extends Model<Collection> {

    private static final long serialVersionUID = 1L;

    /**
     * Collection ID, collection of single, multiple contents
     */
    @TableId(value = "cid", type = IdType.UUID)
    private String cid;

    /**
     * This collection belongs to which user id
     */
    private Integer uid;

    /**
     * Title of the collection
     */
    private String title;

    /**
     * Description of the collection
     */
    private String description;

    private String collectionType;

    private LocalDateTime createTime;

    private String tag;

    private Boolean isDraft;

    private String coverImg;

    private Integer view;

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
    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
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
    public Boolean getDraft() {
        return isDraft;
    }

    public void setDraft(Boolean isDraft) {
        this.isDraft = isDraft;
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
    protected Serializable pkVal() {
        return this.cid;
    }

    @Override
    public String toString() {
        return "Collection{" +
        "cid=" + cid +
        ", uid=" + uid +
        ", title=" + title +
        ", description=" + description +
        ", collectionType=" + collectionType +
        ", createTime=" + createTime +
        ", tag=" + tag +
        ", isDraft=" + isDraft +
        ", coverImg=" + coverImg +
        ", view=" + view +
        "}";
    }
}
