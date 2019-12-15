package com.bndrs.voice.common;

import javax.servlet.http.HttpServletRequest;

public class PageUtil {
    /**
     * 查询操作前，设置通用查询属性
     * @param request
     */
    public static BaseEntity setBaseEntityByQuery(HttpServletRequest request) {
        BaseEntity baseEntity = new BaseEntity();
        baseEntity.setPageNumber(getParamValue(request,"pageNumber"));
        baseEntity.setPageSize(getParamValue(request,"pageSize"));
        return baseEntity;
    }

    /**
     * 从contextMap中获取参数
     * @param key	参数key
     * @return		参数value
     */
    public static Integer getParamValue(HttpServletRequest request,String key){
        String paramValue = request.getParameter(key);
       if(StringUtil.isNotNullOrEmpty(paramValue)) {
           return  Integer.parseInt(paramValue) ;
       }
       else {
           return Constants.PAGE_SIZE;
       }
    }
}
