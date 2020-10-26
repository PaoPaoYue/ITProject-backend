package com.ypp.itproject.mapper;

import com.ypp.itproject.entity.Collection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * Collection of the contents Mapper 接口
 * </p>
 *
 * @author ethan
 * @since 2020-10-16
 */
public interface CollectionMapper extends BaseMapper<Collection> {

    boolean updateView(@Param("cid") String cid);
}
