package com.ypp.itproject.vo;

import com.ypp.itproject.entity.FileContent;

import javax.validation.constraints.NotNull;

public class UpdateFileContentVo {

    @NotNull
    private String file;

    public UpdateFileContentVo() {}

    public FileContent toFileContent() {
        FileContent fileContent = new FileContent();
        fileContent.setFile(this.file);
        return fileContent;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "UpdatePostFileVo{" +
                "file='" + file + '\'' +
                '}';
    }
}
