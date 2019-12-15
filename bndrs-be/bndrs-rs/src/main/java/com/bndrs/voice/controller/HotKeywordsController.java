package com.bndrs.voice.controller;

import com.bndrs.voice.common.ApiResponseEntity;
import com.bndrs.voice.service.HotKeywordsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hot")
@Api(value = "/hot", description = "热门词汇管理")
public class HotKeywordsController {

    @Autowired
    private HotKeywordsService hotKeywordsService;

    @GetMapping("/query.do")
    @ApiOperation(value = "查询热门词汇", notes = "查询热门词汇信息", tags = "热门词汇管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public ApiResponseEntity query() {
        return  hotKeywordsService.query();
    }
}
