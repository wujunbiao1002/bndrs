package com.bndrs.voice.service;

import com.bndrs.voice.common.ApiResponseEntity;
import com.bndrs.voice.entity.NetdiskResourceEntity;

public interface NetdiskResourceService {
    ApiResponseEntity save(NetdiskResourceEntity netdiskResourceEntity);
}
