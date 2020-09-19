package com.ypp.itproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ethan
 * @since 2020-09-20
 */
public class Content extends Model<Content> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;

    /**
     * Links of the content
     */
    private String link;

    /**
     * User ID this content belongs to
     */
    private String uid;

    /**
     * The name of the content
     */
    private String filename;

    /**
     * Type of the content
     */
    private String filetype;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getFiletype() {
        return filetype;
    }

    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }

    @Override
    protected Serializable pkVal() {
        return this.cid;
    }

    @Override
    public String toString() {
        return "Content{" +
        "cid=" + cid +
        ", link=" + link +
        ", uid=" + uid +
        ", filename=" + filename +
        ", filetype=" + filetype +
        "}";
    }
}
