package com.bndrs.voice.controller;

import com.bndrs.voice.common.ApiResponseEntity;
import com.bndrs.voice.service.FastsosoUrlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/fastsoso")
@Api(value = "/fastsoso", description = "Fastsoso爬虫管理")
public class FastsosoUrlController {
    @Autowired
    private FastsosoUrlService fastsosoUrlService;
    @GetMapping("add.do/{keywords}")
    @ApiOperation(value = "Fastsoso关键字添加", notes = "Fastsoso关键字添加", tags = "Fastsoso爬虫管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public ApiResponseEntity add(@PathVariable(value = "keywords") String keywords) {
        return fastsosoUrlService.save(keywords);
    }
}
