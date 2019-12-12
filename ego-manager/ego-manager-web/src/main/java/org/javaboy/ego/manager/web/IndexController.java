package org.javaboy.ego.manager.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {
    @GetMapping("/{index}")
    public String index(@PathVariable String index) {
        return index;
    }
}
