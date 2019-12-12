package org.javaboy.ego.manager.web;

import org.javaboy.ego.manager.pojo.TbContent;
import org.javaboy.ego.manager.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TbContentController {
    @Autowired
    TbContentService tbContentService;
    @GetMapping("/content/query/list")
    public List<TbContent> getAllContentByPage(Long categoryId, Integer page, Integer rows) {
        return tbContentService.getAllContentByPage(categoryId, page, rows);
    }
}
