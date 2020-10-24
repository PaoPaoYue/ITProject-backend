package com.ypp.itproject.vo;

public class PostVo {

    private PostPreviewVo preview;
    private ContentVo content;

    public PostVo() {
    }

    public PostVo(PostPreviewVo preview, ContentVo content) {
        this.preview = preview;
        this.content = content;
    }

    public PostPreviewVo getPreview() {
        return preview;
    }

    public void setPreview(PostPreviewVo preview) {
        this.preview = preview;
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
                "preview=" + preview +
                ", content=" + content +
                '}';
    }
}
