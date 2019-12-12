package org.javaboy.ego.manager.service;

import org.javaboy.commons.TreeData;
import org.javaboy.ego.manager.mapper.TbContentCategoryMapper;
import org.javaboy.ego.manager.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbContentCategoryService {
    @Autowired
    TbContentCategoryMapper tbContentCategoryMapper;
    public List<TreeData> getAllContentCategoryByPid(Long id) {
        List<TbContentCategory> list = tbContentCategoryMapper.getAllContentCategoryByPid(id);
        List<TreeData> result = new ArrayList<TreeData>();
        for (TbContentCategory tbContentCategory : list) {
            TreeData e = new TreeData();
            e.setText(tbContentCategory.getName());
            e.setState(tbContentCategory.getIsParent() ? "closed" : "open");
            e.setId(tbContentCategory.getId());
            result.add(e);
        }
        return result;
    }

    public Integer addContentCategory(TbContentCategory tbContentCategory) {
        tbContentCategory.setIsParent(false);
        return tbContentCategoryMapper.insertSelective(tbContentCategory);
    }

}
