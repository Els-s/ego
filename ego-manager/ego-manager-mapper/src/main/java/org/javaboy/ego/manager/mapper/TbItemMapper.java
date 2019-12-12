package org.javaboy.ego.manager.mapper;

import org.apache.ibatis.annotations.Param;
import org.javaboy.ego.manager.pojo.TbItem;

import java.util.List;

public interface TbItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    TbItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);

    List<TbItem> getTbItemByPage(@Param("page") Integer page, @Param("rows") Integer rows);

    Long getTotal();
}