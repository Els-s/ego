package org.javaboy.egosearch.controller;

import org.javaboy.ego.manager.pojo.SearchResult;
import org.javaboy.egosearch.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@RestController
public class SearchController {
    @Autowired
    SearchService searchService;
    @GetMapping("/search")
    public SearchResult search(@RequestParam("kw") String queryString, @RequestParam(value = "page",defaultValue = "1") Integer page, HttpServletResponse resp) throws UnsupportedEncodingException {
//        queryString = new String(queryString.getBytes("ISO-8859-1"), "UTF-8");
        return searchService.search(queryString, page);
    }
}
