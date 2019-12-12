package org.javaboy.egosearch.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.javaboy.ego.manager.pojo.SearchResult;
import org.javaboy.ego.manager.pojo.TbItem;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SearchDao {
    HttpSolrServer server = new HttpSolrServer("http://localhost:8080/solr");

    public SearchResult search(SolrQuery solrQuery) {
        SearchResult searchResult = new SearchResult();
        List<TbItem> list = new ArrayList<>();
        try {
            QueryResponse response = server.query(solrQuery);
            SolrDocumentList results = response.getResults();//获取查询结果
            long numFound = results.getNumFound();//查询到的记录数
            for (SolrDocument document : results) {
                Long id = Long.parseLong((String) document.get("id"));
                String item_title = (String) document.get("item_title");
                String item_sell_point = (String) document.get("item_sell_point");
                Long item_price = (Long) document.get("item_price");
                String item_image = (String) document.get("item_image");
                TbItem tbItem = new TbItem();
                tbItem.setId(id);
                tbItem.setTitle(item_title);
                tbItem.setSellPoint(item_sell_point);
                tbItem.setPrice(item_price);
                tbItem.setImage(item_image);
                list.add(tbItem);
            }
            searchResult.setRecordCount(numFound);
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        searchResult.setItemList(list);
        return searchResult;
    }
}
