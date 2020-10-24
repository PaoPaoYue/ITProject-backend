package com.ypp.itproject.vo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class NewPostVo {
    @NotNull
    private ContentEnum collectionType;
    @NotBlank @Size(max = 200)
    private String title;

    public ContentEnum getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(ContentEnum collectionType) {
        this.collectionType = collectionType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "NewPostVo{" +
                "collectionType=" + collectionType +
                ", title='" + title + '\'' +
                '}';
    }
}
