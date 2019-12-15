package com.bndrs.voice.impl;

import com.bndrs.voice.common.ApiResponseEntity;
import com.bndrs.voice.contants.StatusEnum;
import com.bndrs.voice.dao.HotKeywordsEntityMapper;
import com.bndrs.voice.entity.HotKeywordsEntity;
import com.bndrs.voice.example.HotKeywordsEntityExample;
import com.bndrs.voice.service.HotKeywordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotKeywordsServiceImpl implements HotKeywordsService {

    @Autowired
    private HotKeywordsEntityMapper hotKeywordsEntityMapper;

    @Override
    public ApiResponseEntity query() {
        ApiResponseEntity apiResponseEntity = new ApiResponseEntity();
        HotKeywordsEntityExample hotKeywordsEntityExample = new HotKeywordsEntityExample();
        HotKeywordsEntityExample.Criteria criteria = hotKeywordsEntityExample.createCriteria();
        criteria.andStatusNotEqualTo(StatusEnum.DELETE.getName());

        List<HotKeywordsEntity> hotKeywordsEntities = hotKeywordsEntityMapper.selectByExample(hotKeywordsEntityExample);
        apiResponseEntity.setData(hotKeywordsEntities);
        apiResponseEntity.success();
        return apiResponseEntity;
    }
}
