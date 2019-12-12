package org.javaboy.egorest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.discovery.converters.Auto;
import org.javaboy.commons.AdItem;
import org.javaboy.egorest.service.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class AdController {
    @Autowired
    AdService adService;
    @GetMapping("/ads")
    public String getAdsByCid(Long id) throws JsonProcessingException {
        List<AdItem> list = adService.getAdsByCid(id);
        return new ObjectMapper().writeValueAsString(list);
    }
}
