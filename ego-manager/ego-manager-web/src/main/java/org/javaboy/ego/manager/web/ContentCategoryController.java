package org.javaboy.ego.manager.web;

import org.javaboy.commons.RespBean;
import org.javaboy.commons.TreeData;
import org.javaboy.ego.manager.pojo.TbContentCategory;
import org.javaboy.ego.manager.service.TbContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContentCategoryController {
    @Autowired
    TbContentCategoryService tbContentCategoryService;
    @GetMapping("/content/category/list")
    public List<TreeData> getAllContentCategoryByPid(@RequestParam(defaultValue = "0") Long id) {
        return tbContentCategoryService.getAllContentCategoryByPid(id);
    }

    @PostMapping("/content/category/create")
    public RespBean addContentCategory(TbContentCategory tbContentCategory) {
        if (tbContentCategoryService.addContentCategory(tbContentCategory) == 1) {
            return RespBean.ok("添加成功",tbContentCategory);
        }
        return RespBean.error("添加失败");
    }
}
