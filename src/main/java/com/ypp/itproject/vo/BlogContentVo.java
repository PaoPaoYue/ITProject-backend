package com.ypp.itproject.vo;

import com.ypp.itproject.entity.BlogContent;

import javax.validation.constraints.NotNull;

public class BlogContentVo extends ContentVo {

    private String text;

    public BlogContentVo() {}

    public BlogContentVo(BlogContent blogContent) {
        this.text = blogContent.getText();
        this.lastUpdate = blogContent.getLastUpdate();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "BlogContentVo{" +
                "text='" + text + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
