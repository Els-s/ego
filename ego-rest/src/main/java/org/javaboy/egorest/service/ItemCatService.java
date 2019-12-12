package org.javaboy.egorest.service;

import org.javaboy.ego.manager.mapper.TbItemCatMapper;
import org.javaboy.ego.manager.pojo.TbItemCat;
import org.javaboy.egorest.model.ItemCat;
import org.javaboy.egorest.model.ItemCatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatService {
    @Autowired
    TbItemCatMapper tbItemCatMapper;
    public ItemCatResult getAllItemCat() {
        ItemCatResult itemCatResult = new ItemCatResult();
        itemCatResult.setData(getItemCatByPid(0L));
        return itemCatResult;
    }

    private List<?> getItemCatByPid(long pid) {
        List result = new ArrayList();
        List<TbItemCat> list = tbItemCatMapper.getItemCatByPid(pid);
        for (int i = 0; i < list.size(); i++) {
            TbItemCat tbItemCat = list.get(i);
            if (tbItemCat.getIsParent()) {
                ItemCat e = new ItemCat();
                e.setU("/category/" + tbItemCat.getId() + ".html");
                e.setN(tbItemCat.getName());
                e.setI(getItemCatByPid(tbItemCat.getId()));
                result.add(e);
            }else{
                result.add("/item/" + tbItemCat.getId() + ".html|" + tbItemCat.getName());
            }
        }
        return result;
    }
}
