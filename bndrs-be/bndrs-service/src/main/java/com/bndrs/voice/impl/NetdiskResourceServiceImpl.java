package com.bndrs.voice.impl;

import com.bndrs.voice.common.ApiResponseEntity;
import com.bndrs.voice.common.DateUtil;
import com.bndrs.voice.common.UUIDGenerator;
import com.bndrs.voice.contants.StatusEnum;
import com.bndrs.voice.dao.NetdiskResourceEntityMapper;
import com.bndrs.voice.entity.NetdiskResourceEntity;
import com.bndrs.voice.service.NetdiskResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NetdiskResourceServiceImpl implements NetdiskResourceService {
    private Logger logger = LoggerFactory.getLogger(NetdiskResourceServiceImpl.class);

    @Autowired
    private NetdiskResourceEntityMapper netdiskResourceEntityMapper;

    @Override
    public ApiResponseEntity save(NetdiskResourceEntity netdiskResourceEntity) {
        ApiResponseEntity apiResponseEntity = new ApiResponseEntity();
        netdiskResourceEntity.setId(UUIDGenerator.getUUID());
        netdiskResourceEntity.setCreatedDate(DateUtil.getNowTime());
        netdiskResourceEntity.setStatus(StatusEnum.NORMAL.getName());
        try {

            netdiskResourceEntityMapper.insert(netdiskResourceEntity);
            apiResponseEntity.success();
            apiResponseEntity.setMessage("共享百度网盘资源链接成功");
        }catch (Exception e){

            logger.error("监听方法={}, 异常信息为={}", "NetdiskResourceServiceImpl.save", e.getMessage(), e);
            apiResponseEntity.setCode("999");
            apiResponseEntity.setMessage("已经存在此百度网盘连接");
        }
        return apiResponseEntity;
    }
}
