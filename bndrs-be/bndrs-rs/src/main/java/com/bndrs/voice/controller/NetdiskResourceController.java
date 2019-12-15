package com.bndrs.voice.controller;

import com.bndrs.voice.common.ApiResponseEntity;
import com.bndrs.voice.entity.NetdiskResourceEntity;
import com.bndrs.voice.service.NetdiskResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resource")
@Api(value = "/resource", description = "百度网盘资源管理")
public class NetdiskResourceController {
    @Autowired
    private NetdiskResourceService netdiskResourceService;

    @PostMapping("/add.do")
    @ApiOperation(value = "百度网盘资源添加", notes = "百度网盘资源添加", tags = "百度网盘资源管理")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful Operation"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal server error")})
    public ApiResponseEntity add(@RequestParam String title,
                                 @RequestParam String url,
                                 @RequestParam String type,
                                 String password) {
        NetdiskResourceEntity netdiskResourceEntity = new NetdiskResourceEntity();
        netdiskResourceEntity.setTitle(title);
        netdiskResourceEntity.setUrl(url);
        netdiskResourceEntity.setType(type);
        netdiskResourceEntity.setPassword(password);
        return  netdiskResourceService.save(netdiskResourceEntity);
    }
}
