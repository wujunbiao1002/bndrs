package com.bndrs.voice.exception;

import java.text.MessageFormat;

/**
 * @author ljg
 */
public class WechatOpenApiException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    /**
     * 错误信息模板
     */
    private final static String MSG_TEMPLATE = "错误码:{0}, 描述:{1}";
    private final static String MSG_FULL_TEMPLATE = "错误码:{0}, 描述:{1}, 异常信息:{2}";

    private WechatOpenApiErrorEnum errorEnum;
    private String exMsg;
    public WechatOpenApiException(WechatOpenApiErrorEnum errorEnum)
    {
        super(MessageFormat.format(MSG_TEMPLATE, errorEnum.getCode(), errorEnum.getMessage()));
        this.errorEnum = errorEnum;
    }

    public WechatOpenApiException(WechatOpenApiErrorEnum errorEnum, String exMsg)
    {
        super(MessageFormat.format(MSG_FULL_TEMPLATE, errorEnum.getCode(), errorEnum.getMessage(), exMsg));
        this.errorEnum = errorEnum;
        this.exMsg = exMsg;
    }

    public WechatOpenApiErrorEnum getErrorEnum()
    {
        return errorEnum;
    }

    public String getExMsg()
    {
        return exMsg;
    }
}
