package com.ypp.itproject.service;

import com.ypp.itproject.entity.FileContent;
import com.baomidou.mybatisplus.extension.service.IService;

public interface IFileContentService extends IService<FileContent> {
    boolean updateContent(FileContent fileContent);
}
