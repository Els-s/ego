package org.javaboy.ego.manager.mapper;

import org.javaboy.ego.manager.pojo.TbItemCat;

import java.util.List;

public interface TbItemCatMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbItemCat record);

    int insertSelective(TbItemCat record);

    TbItemCat selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbItemCat record);

    int updateByPrimaryKey(TbItemCat record);

    List<TbItemCat> getItemCatByPid(Long pid);
}