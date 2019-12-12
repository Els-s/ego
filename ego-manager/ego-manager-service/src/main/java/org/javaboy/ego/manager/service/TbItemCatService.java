package org.javaboy.ego.manager.service;

import org.javaboy.commons.TreeData;
import org.javaboy.ego.manager.mapper.TbItemCatMapper;
import org.javaboy.ego.manager.pojo.TbItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TbItemCatService {
    @Autowired
    TbItemCatMapper tbItemCatMapper;
    public List<TreeData> getItemCatByPid(Long pid) {
        List<TbItemCat> list = tbItemCatMapper.getItemCatByPid(pid);
        List<TreeData> treeDatas = new ArrayList<TreeData>(list.size());
        for (TbItemCat tbItemCat : list) {
            TreeData e = new TreeData();
            e.setId(tbItemCat.getId());
            e.setState(tbItemCat.getIsParent() ? "closed" : "open");
            e.setText(tbItemCat.getName());
            treeDatas.add(e);
        }
        return treeDatas;
    }
}
