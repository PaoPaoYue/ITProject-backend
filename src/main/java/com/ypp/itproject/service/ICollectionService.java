package com.ypp.itproject.service;

import com.ypp.itproject.entity.Collection;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * Collection of the contents 服务类
 * </p>
 *
 * @author ethan
 * @since 2020-10-16
 */
public interface ICollectionService extends IService<Collection> {

    boolean updateCollection(Collection collection);
    boolean updateView(String cid);
    List<Collection> getAll(int uid);
    List<Collection> getTops(int uid, int limit);
    List<Collection> getDrafts(int uid);
}
