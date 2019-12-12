package org.javaboy.egorest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.javaboy.egorest.model.ItemCatResult;
import org.javaboy.egorest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemCatController {
    @Autowired
    ItemCatService itemCatService;
    @GetMapping("/item/all")
    public String getItemCat(String callback) throws JsonProcessingException {
        ItemCatResult allItemCat = itemCatService.getAllItemCat();
        //  callback({})
        return callback+"("+new ObjectMapper().writeValueAsString(allItemCat) +")";
    }
}
