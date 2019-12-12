package org.javaboy.ego.manager.mapper;

import org.apache.ibatis.annotations.Param;
import org.javaboy.ego.manager.pojo.TbContent;

import java.util.List;

public interface TbContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TbContent record);

    int insertSelective(TbContent record);

    TbContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbContent record);

    int updateByPrimaryKeyWithBLOBs(TbContent record);

    int updateByPrimaryKey(TbContent record);

    List<TbContent> getAllContentByPage(@Param("categoryId") Long categoryId, @Param("page") Integer page, @Param("rows") Integer rows);
}