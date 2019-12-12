package org.javaboy.ego.manager.service;

import org.javaboy.ego.manager.mapper.TbContentMapper;
import org.javaboy.ego.manager.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TbContentService {
    @Autowired
    TbContentMapper tbContentMapper;
    public List<TbContent> getAllContentByPage(Long categoryId, Integer page, Integer rows) {
        if (page != null && rows != null) {
            page = (page - 1) * rows;
        }
        return tbContentMapper.getAllContentByPage(categoryId, page, rows);
    }
}
