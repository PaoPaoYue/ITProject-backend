package com.ypp.itproject.vo;

public class PostVo {

    private PostPreviewVo info;
    private ContentVo content;

    public PostVo() {
    }

    public PostVo(PostPreviewVo info, ContentVo content) {
        this.info = info;
        this.content = content;
    }

    public PostPreviewVo getInfo() {
        return info;
    }

    public void setInfo(PostPreviewVo info) {
        this.info = info;
    }

    public ContentVo getContent() {
        return content;
    }

    public void setContent(ContentVo content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PostVo{" +
                "info=" + info +
                ", content=" + content +
                '}';
    }
}
