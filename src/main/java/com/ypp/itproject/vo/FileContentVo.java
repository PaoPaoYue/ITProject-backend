package com.ypp.itproject.vo;

import com.ypp.itproject.entity.FileContent;

import javax.validation.constraints.NotNull;

public class FileContentVo extends ContentVo {

    private String file;

    public FileContentVo() {}

    public FileContentVo(FileContent fileContent) {
        this.file = fileContent.getFile();
        this.lastUpdate = fileContent.getLastUpdate();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "FileContentVo{" +
                "file='" + file + '\'' +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
