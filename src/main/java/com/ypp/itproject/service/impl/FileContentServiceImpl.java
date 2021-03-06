package com.ypp.itproject.service.impl;

import com.ypp.itproject.entity.FileContent;
import com.ypp.itproject.mapper.FileContentMapper;
import com.ypp.itproject.service.IFileContentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class FileContentServiceImpl extends ServiceImpl<FileContentMapper, FileContent> implements IFileContentService {

    @Override
    public boolean updateContent(FileContent fileContent) {
        return this.updateById(fileContent);
    }
}
