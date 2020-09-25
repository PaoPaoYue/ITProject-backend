package com.ypp.itproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * Collection of the contents
 * </p>
 *
 * @author ethan
 * @since 2020-09-22
 */
public class Collection extends Model<Collection> {

    private static final long serialVersionUID = 1L;

    /**
     * Collection ID, collection of single, multiple contents
     */
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

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

    private LocalDate createDate;

    private String tag;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
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
    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
        ", createDate=" + createDate +
        ", tag=" + tag +
        "}";
    }
}
