package org.javaboy.egosearch;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.javaboy.ego.manager.mapper.TbItemMapper;
import org.javaboy.ego.manager.pojo.TbItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@SpringBootTest
class EgoSearchApplicationTests {

    @Autowired
    TbItemMapper itemMapper;
    @Test
    void contextLoads() throws IOException, SolrServerException {
        HttpSolrServer solrServer = new HttpSolrServer("http://localhost:8080/solr");
        List<TbItem> tbItemByPage = itemMapper.getTbItemByPage(null, null);
        for (TbItem tbItem : tbItemByPage) {
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField("id",tbItem.getId());
            doc.addField("item_title",tbItem.getTitle());
            doc.addField("item_sell_point",tbItem.getSellPoint());
            doc.addField("item_price",tbItem.getPrice());
            doc.addField("item_image",tbItem.getImage());
//            doc.addField("item_category_name",tbItem.get);
//            doc.addField("item_keywords",tbItem.);
            solrServer.add(doc);
        }
            solrServer.commit();
    }
}