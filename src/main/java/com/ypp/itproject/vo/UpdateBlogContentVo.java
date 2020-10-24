package com.ypp.itproject.vo;

import com.ypp.itproject.entity.BlogContent;

import javax.validation.constraints.NotNull;

public class UpdateBlogContentVo {

    @NotNull
    private String text;

    public UpdateBlogContentVo() {}

    public BlogContent toBlogContent() {
        BlogContent fileContent = new BlogContent();
        fileContent.setText(this.text);
        return fileContent;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "UpdatePostBlogVo{" +
                "text='" + text + '\'' +
                '}';
    }
}
