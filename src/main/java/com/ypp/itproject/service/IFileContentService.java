package com.ypp.itproject.service;

import com.ypp.itproject.entity.FileContent;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ypp.itproject.vo.FileContentVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ypp
 * @since 2020-10-23
 */
public interface IFileContentService extends IService<FileContent> {
    boolean updateContent(FileContent fileContent);
}
