package org.javaboy.ego.manager.mapper;

import org.javaboy.ego.manager.pojo.TbContentCategory;

import java.util.List;

public interface TbContentCategoryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbContentCategory record);

    int insertSelective(TbContentCategory record);

    TbContentCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbContentCategory record);

    int updateByPrimaryKey(TbContentCategory record);

    List<TbContentCategory> getAllContentCategoryByPid(Long id);
}