package org.javaboy.egoportal.feign;

import org.javaboy.ego.manager.pojo.SearchResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ego-search")
public interface SearchFeign {
    @GetMapping("/search")
    SearchResult search(@RequestParam("kw") String queryString, @RequestParam(value = "page", defaultValue = "1") Integer page);
}
