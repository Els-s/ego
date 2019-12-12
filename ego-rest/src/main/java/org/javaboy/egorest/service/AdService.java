package org.javaboy.egorest.service;

import org.javaboy.commons.AdItem;
import org.javaboy.ego.manager.mapper.TbContentMapper;
import org.javaboy.ego.manager.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdService {
    @Autowired
    TbContentMapper tbContentMapper;
    @Autowired
    RedisTemplate redisTemplate;
    public List<AdItem> getAdsByCid(Long id) {
        List<AdItem> list2 = null;
        HashOperations hash = redisTemplate.opsForHash();
        list2 = (List<AdItem>) hash.get("ads", "big_ad");
        if (list2 != null && list2.size() > 0) {
            System.out.println(">>>>>>>>>>>redis>>>>>>>>>>>>>>");
            return list2;
        }
        List<TbContent> list = tbContentMapper.getAllContentByPage(id, null, null);
            System.out.println(">>>>>>>>>>>db>>>>>>>>>>>>>>");
        List<AdItem> result = new ArrayList<>(list.size());
        for (TbContent tbContent : list) {
            AdItem adItem = new AdItem();
            adItem.setSrc(tbContent.getPic());
            adItem.setHeight(240);
            adItem.setWidth(670);
            adItem.setSrcB(tbContent.getPic2());
            adItem.setHeightB(240);
            adItem.setWidthB(550);
            adItem.setAlt(tbContent.getTitleDesc());
            adItem.setHref(tbContent.getUrl());
            result.add(adItem);
        }
        hash.put("ads", "big_ad", result);
        return result;
    }
}
