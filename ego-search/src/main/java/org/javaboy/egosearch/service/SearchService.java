package org.javaboy.egosearch.service;

import org.apache.solr.client.solrj.SolrQuery;
import org.javaboy.ego.manager.pojo.SearchResult;
import org.javaboy.egosearch.dao.SearchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
    @Autowired
    SearchDao searchDao;

    public SearchResult search(String queryString, Integer page) {
        SolrQuery solrQuery = new SolrQuery();
        if (queryString != null && !"".equals(queryString)) {
            solrQuery.setQuery(queryString);
        } else {
            solrQuery.setQuery("*:*");
        }
        solrQuery.setStart((page - 1) * 30);//分页查询，起始记录数
        solrQuery.setRows(30);//分页查询总记录数
        solrQuery.setHighlight(true);//开启高亮（搜索关键字换个颜色展示）
        solrQuery.addHighlightField("item_title");//高亮显示的域
        solrQuery.setHighlightSimplePre("<em style=\"color:red;\">");//高亮显示的前缀
        solrQuery.setHighlightSimplePost("</em>");//高亮展示的后缀
        solrQuery.set("df", "item_title");
        SearchResult result = searchDao.search(solrQuery);
        result.setCurPage(page);
        long l = result.getRecordCount() / 30;
        if (result.getRecordCount() % 30 > 0) {
            l++;
        }
        result.setPageCount((int) l);
        return result;
    }
}
