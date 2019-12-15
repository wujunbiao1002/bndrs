package com.bndrs.voice.controller;

import com.bndrs.voice.common.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/search")
@Api(value = "/search", description = "百度网盘资源搜索引擎")
public class SearchController {
    private Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SolrClient solrClient;

    @PostMapping("/query.do")
    @ApiOperation(value = "百度网盘资源搜索", notes = "百度网盘资源搜索", tags = "百度网盘资源搜索引擎")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Map<String, Object> search(@RequestParam String keywords,
                                      @RequestParam String pageNumber,
                                      @RequestParam String pageSize,
                                      String type,
                                      String sort) {

        try {
            SolrQuery params = new SolrQuery();
            // 查询关键字
            params.setQuery(keywords);
            // 过滤条件：类型
            if (StringUtil.isNotNullOrEmpty(type)) {
                String typeStr = "type:" + type;
                params.set("fq", typeStr);
            }
            // 分页
            params.setStart((Integer.valueOf(pageNumber) - 1) * 10);
            params.setRows(Integer.valueOf(pageSize));
            // 排序分享时间降序
            if ("sort".equals(sort)){
                params.setSort("share_date", SolrQuery.ORDER.desc);
            }
            // 默认域默认是text
//            params.set("df", "text");
            // 只查询指定域，显示制定的域字段
//            params.set("fl", "title");
            // 高亮打开开关
/*            params.setHighlight(true);
            // 指定高亮域
            params.addHighlightField("title");
            // 设置前缀
            params.setHighlightSimplePre("<span style='color:red'>");
            // 设置后缀
            params.setHighlightSimplePost("</span>");*/
            long start = System.currentTimeMillis();
            // 执行查询
            QueryResponse queryResponse = solrClient.query(params);
            long end = System.currentTimeMillis();
            // 获取文档列表
            SolrDocumentList results = queryResponse.getResults();
            //  数量，分页用
            long total = results.getNumFound();// JS 使用 size=MXA 和 data.length 即可知道长度了（但不合理）
            Map<String, Object> map = new HashMap<>();
            map.put("time", (double)(end - start)/1000);
            map.put("data", results);
            map.put("total", total);
            return map;

        } catch (Exception e) {

            logger.error("监听方法={}, 异常信息为={}", "SearchController.search", e.getMessage(), e);
        }
        return null;
    }

    @PostMapping("/user-share.do")
    @ApiOperation(value = "用户分享资源搜索", notes = "用户分享的百度网盘资源搜索", tags = "百度网盘资源搜索引擎")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public Map<String, Object> searchByUserId(@RequestParam String userId,
                                      @RequestParam String pageNumber,
                                      @RequestParam String pageSize,
                                      String sort) {

        try {
            SolrQuery params = new SolrQuery();
            params.setQuery("*:*");

            // 过滤条件：类型
            String userIdStr = "share_id:" + userId;
            params.set("fq", userIdStr);
            // 分页
            params.setStart((Integer.valueOf(pageNumber) - 1) * 10);
            params.setRows(Integer.valueOf(pageSize));
            // 排序分享时间降序
            if ("sort".equals(sort)){
                params.setSort("share_date", SolrQuery.ORDER.desc);
            }
            // 默认域默认是text
//            params.set("df", "text");
            // 只查询指定域，显示制定的域字段
//            params.set("fl", "title");
            // 高亮打开开关
/*            params.setHighlight(true);
            // 指定高亮域
            params.addHighlightField("title");
            // 设置前缀
            params.setHighlightSimplePre("<span style='color:red'>");
            // 设置后缀
            params.setHighlightSimplePost("</span>");*/
            long start = System.currentTimeMillis();
            // 执行查询
            QueryResponse queryResponse = solrClient.query(params);
            long end = System.currentTimeMillis();
            // 获取文档列表
            SolrDocumentList results = queryResponse.getResults();
            //  数量，分页用
            long total = results.getNumFound();// JS 使用 size=MXA 和 data.length 即可知道长度了（但不合理）
            Map<String, Object> map = new HashMap<>();
            map.put("time", (double)(end - start)/1000);
            map.put("data", results);
            map.put("total", total);
            return map;

        } catch (Exception e) {

            logger.error("监听方法={}, 异常信息为={}", "SearchController.search", e.getMessage(), e);
        }
        return null;
    }

}

