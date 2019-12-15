package com.bndrs.voice.common;

import java.beans.Transient;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BaseEntity<T> implements Serializable {

    private Integer pageNumber = 1; //当前页码

    private Integer pageSize = 10;  //默认分页记录数

    private T entity;

    //用来存放其它查询条件，mapper使用<if test="paramMap.xxx!=null ">进行条件拼接
    private Map<String,Object> paramMap = new HashMap<String,Object>();

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
