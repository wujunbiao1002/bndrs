package com.bndrs.voice.controller;

import com.bndrs.voice.ControllerApplication;
import com.bndrs.voice.common.StringUtil;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = ControllerApplication.class)
@RunWith(SpringRunner.class)
public class SearchControllerTest {
    private Logger logger = LoggerFactory.getLogger(SearchControllerTest.class);

    @Autowired
    private SolrClient solrClient;

    @Test
    public void select() {
        try {
            SolrQuery params = new SolrQuery();
            // 查询关键字
            params.setQuery("流浪地球");
            // 过滤条件：类型
            if (StringUtil.isNotNullOrEmpty("")) {
                String typeStr = "type:音乐";
                params.set("fq", typeStr);
            }
            // 分页
            params.setStart(0);
            params.setRows(10);
            // 高亮打开开关
            params.setHighlight(true);
            // 指定高亮域
            params.addHighlightField("title");
            // 设置前缀
            params.setHighlightSimplePre("<span style='color:red'>");
            // 设置后缀
            params.setHighlightSimplePost("</span>");
            // 执行查询
            QueryResponse queryResponse = solrClient.query(params);
            // 获取文档列表
            SolrDocumentList docs = queryResponse.getResults();
            //  数量，分页用
            long count = docs.getNumFound();// JS 使用 size=MXA 和 data.length 即可知道长度了（但不合理）

            //高亮集合
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            System.out.println(highlighting);
            //高亮集合不为空的话把普通字段替换为高亮字段
            if(highlighting!=null) {
                List<Map<String, Object>> list = new ArrayList<>();
                Map<String, Object> maps;
                for (int i = 0; i < docs.size(); i++) {
                    maps = new HashMap<>();
                    List<String> strings = highlighting.get(docs.get(i).get("id")).get("title");
                    maps.put("id", docs.get(i).get("id"));
                    maps.put("url", docs.get(i).get("url"));
                    maps.put("title", docs.get(i).get("title"));
//                    maps.put("title", strings.get(0));
                    System.out.println(strings);
                    list.add(maps);
                }
                Map<String, Object> map = new HashMap<>();
                map.put("doc", list);
                map.put("count", count);
                solrClient.close();
                System.out.println(map);
            }else{
                Map<String, Object> map = new HashMap<>();
                map.put("doc", docs);
                map.put("count", count);
                solrClient.close();
                System.out.println(map);
            }
        } catch (Exception e) {
            logger.error("监听方法={}, 异常信息为={}", "SearchController.search", e.getMessage(), e);
        }
    }

    @Test
    public void fun() {
        SolrQuery params = new SolrQuery();
        params.setQuery("*:*");
        params.set("fq", "c8047effa67adb6b74735beb813ec2b9");
        long start = System.currentTimeMillis();
        // 执行查询
        QueryResponse queryResponse = null;
        try {
            queryResponse = solrClient.query(params);
        } catch (SolrServerException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        long end = System.currentTimeMillis();
        // 获取文档列表
        SolrDocumentList results = queryResponse.getResults();
        //  数量，分页用
        long total = results.getNumFound();// JS 使用 size=MXA 和 data.length 即可知道长度了（但不合理）
        Map<String, Object> map = new HashMap<>();
        map.put("time", (double)(end - start)/1000);
        map.put("data", results);
        map.put("total", total);
        System.out.println(map);
        System.out.println(total);
        System.out.println("lao");
    }

}