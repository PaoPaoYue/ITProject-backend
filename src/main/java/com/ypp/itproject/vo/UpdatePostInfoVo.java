package com.ypp.itproject.vo;

import com.ypp.itproject.entity.Collection;

import javax.validation.constraints.*;
import java.util.Set;

public class UpdatePostInfoVo {
    @NotBlank @Size(max = 200)
    private String title;
    @NotNull @Size(max = 400)
    private String description;
    @NotNull
    private Set<String> tag;
    @NotNull
    private Boolean isDraft;
    @NotNull @Size(max = 2083)
    private String coverImg;

    public UpdatePostInfoVo() {
    }

    public Collection toCollection() {
        Collection collection = new Collection();
        collection.setTitle(this.title);
        collection.setDescription(this.description);
        collection.setTag(this.tag.stream().reduce((a, b)-> (a + "," + b)).get());
        collection.setDraft(this.isDraft);
        collection.setCoverImg(this.coverImg);
        return collection;
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

    public Set<String> getTag() {
        return tag;
    }

    public void setTag(Set<String> tag) {
        this.tag = tag;
    }

    public Boolean getDraft() {
        return isDraft;
    }

    public void setDraft(Boolean draft) {
        isDraft = draft;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    @Override
    public String toString() {
        return "CollectionVo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tag='" + tag + '\'' +
                ", isDraft=" + isDraft +
                ", coverImg='" + coverImg + '\'' +
                '}';
    }
}
