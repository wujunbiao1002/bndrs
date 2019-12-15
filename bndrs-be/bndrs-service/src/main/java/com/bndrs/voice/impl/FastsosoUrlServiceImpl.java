package com.bndrs.voice.impl;

import com.bndrs.voice.common.ApiResponseEntity;
import com.bndrs.voice.common.DateUtil;
import com.bndrs.voice.common.UUIDGenerator;
import com.bndrs.voice.contants.StatusEnum;
import com.bndrs.voice.dao.FastsosoUrlEntityMapper;
import com.bndrs.voice.entity.FastsosoUrlEntity;
import com.bndrs.voice.service.FastsosoUrlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FastsosoUrlServiceImpl implements FastsosoUrlService {
    private Logger logger = LoggerFactory.getLogger(FastsosoUrlServiceImpl.class);

    @Autowired
    private FastsosoUrlEntityMapper fastsosoUrlEntityMapper;
    @Override
    public ApiResponseEntity save(String keywords) {
        ApiResponseEntity apiResponseEntity = new ApiResponseEntity();
        FastsosoUrlEntity fastsosoUrlEntity = new FastsosoUrlEntity();
        fastsosoUrlEntity.setCreatedDate(DateUtil.getNowTime());
        fastsosoUrlEntity.setId(UUIDGenerator.getUUID());
        fastsosoUrlEntity.setStatus(StatusEnum.NOTCRAWLING.getName());
        fastsosoUrlEntity.setUrl("https://www.fastsoso.cn/search?k=" + keywords);
        try {

            fastsosoUrlEntityMapper.insert(fastsosoUrlEntity);
            apiResponseEntity.setMessage("添加关键字成功");

        }catch (Exception e){
            logger.error("监听方法={}, 异常信息为={}", "NetdiskResourceServiceImpl.save", e.getMessage(), e);
            apiResponseEntity.setMessage("添加关键字失败");
        }
        apiResponseEntity.success();
        return apiResponseEntity;
    }
}
