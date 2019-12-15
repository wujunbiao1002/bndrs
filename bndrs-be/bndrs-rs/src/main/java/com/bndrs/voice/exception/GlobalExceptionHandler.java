package com.bndrs.voice.exception;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 统一错误处理
 */
@ControllerAdvice
public class GlobalExceptionHandler
{
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Object openApiExceptionHandler(HttpServletRequest request, Exception error) throws Exception
    {
        Map<String, String> map = getSystemErrorMap();
        if ( error != null )
        {
            if ( error instanceof WechatOpenApiException )            {
                WechatOpenApiException apiException = (WechatOpenApiException) error;
                map.put("code", apiException.getErrorEnum().getCode());
                if ( StringUtils.isNotBlank(apiException.getExMsg()) )
                {
                    map.put("message", apiException.getErrorEnum().getMessage() + "(" + apiException.getExMsg() + ")");
                }
                else
                {
                    map.put("message", apiException.getErrorEnum().getMessage());
                }
            }
            else if ( error instanceof HttpRequestMethodNotSupportedException)
            {
                HttpRequestMethodNotSupportedException notSupportedException = (HttpRequestMethodNotSupportedException) error;
                map.put("code", WechatOpenApiErrorEnum.METHOD_NOT_SUPPORTED.getCode());
                map.put("message",notSupportedException.getMessage());
            }
            else
            {
                logger.warn("uri=[{}] is error", request.getRequestURI());
                logger.error("error:", error);
            }
        }
        if ( request.getAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE) != null )
        {
            logger.error("spring mvc error:", request.getAttribute(DispatcherServlet.EXCEPTION_ATTRIBUTE));
        }
        return map;
    }


    private static Map<String, String> getSystemErrorMap()
    {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("code", WechatOpenApiErrorEnum.SYSTEM_ERROR.getCode());
        map.put("message", WechatOpenApiErrorEnum.SYSTEM_ERROR.getMessage());
        return map;
    }


}
