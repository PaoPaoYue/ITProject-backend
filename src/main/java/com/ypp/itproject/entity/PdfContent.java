package com.ypp.itproject.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ethan
 * @since 2020-10-16
 */
public class PdfContent extends Model<PdfContent> {

    private static final long serialVersionUID = 1L;

    private String cid;

    private String link;

    private String filename;

    private String filetype;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
        return "PdfContent{" +
        "cid=" + cid +
        ", link=" + link +
        ", filename=" + filename +
        ", filetype=" + filetype +
        "}";
    }
}
