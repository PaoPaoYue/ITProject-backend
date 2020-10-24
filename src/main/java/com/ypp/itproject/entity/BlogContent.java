package com.ypp.itproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author ethan
 * @since 2020-10-16
 */
public class BlogContent extends Model<BlogContent> {

    private static final long serialVersionUID = 1L;

    @TableId(value="cid", type= IdType.INPUT)
    private String cid;

    private String text;

    private LocalDateTime lastUpdate;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    protected Serializable pkVal() {
        return this.cid;
    }

    @Override
    public String toString() {
        return "BlogContent{" +
                "cid='" + cid + '\'' +
                ", text='" + text + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
