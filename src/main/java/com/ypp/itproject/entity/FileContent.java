package com.ypp.itproject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ypp
 * @since 2020-10-23
 */
public class FileContent extends Model<FileContent> {

    private static final long serialVersionUID = 1L;

    @TableId(value="cid", type=IdType.INPUT)
    private String cid;

    private String file;

    private LocalDateTime lastUpdate;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
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
        return "FileContent{" +
        "cid=" + cid +
        ", file=" + file +
        ", lastUpdate=" + lastUpdate +
        "}";
    }
}
