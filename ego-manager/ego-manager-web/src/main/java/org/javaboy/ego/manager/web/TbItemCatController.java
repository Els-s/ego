package org.javaboy.ego.manager.web;

import org.javaboy.commons.TreeData;
import org.javaboy.ego.manager.service.TbItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TbItemCatController {
    @Autowired
    TbItemCatService tbItemCatService;
    @PostMapping("/item/cat/list")
    public List<TreeData> getItemCatByPid(@RequestParam(defaultValue = "0") Long id) {
        return tbItemCatService.getItemCatByPid(id);
    }
}
