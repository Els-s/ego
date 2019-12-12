package org.javaboy.egoportal;

import org.javaboy.ego.manager.pojo.SearchResult;
import org.javaboy.egoportal.feign.AdFeign;
import org.javaboy.egoportal.feign.SearchFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {
    @Autowired
    AdFeign adFeign;
    @Autowired
    SearchFeign searchFeign;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("ad1", adFeign.getAdsByCid(89L));
        return "index";
    }

    @RequestMapping("/search.html")
    public String searchItemList(@RequestParam(value = "q") String queryString, @RequestParam(defaultValue = "1") Integer page, Model model) throws Exception {
        //字符串转码
//        queryString = new String(queryString.getBytes("ISO8859-1"), "UTF-8");

        SearchResult searchResult = searchFeign.search(queryString, page);
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("page", searchResult.getCurPage());
        model.addAttribute("pages", searchResult.getPageCount());
        return "search";
    }
}
