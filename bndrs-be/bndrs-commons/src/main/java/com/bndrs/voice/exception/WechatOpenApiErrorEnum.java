package com.bndrs.voice.exception;

/**
 * 4**系列code为参数级别错误
 * 5**系列code为业务级别错误
 * 9**系列code为系统级别错误
 */
public enum WechatOpenApiErrorEnum
{

    PARAMETERS_ERROR("400", "参数不合法"), PARAM_NULL("402", "参数为空"), INVALID_CODE("501", "code不合法"), INVALID_TICKET("502", "ticket失效"),INVALID_APPID("503", "appId不合法"), METHOD_NOT_SUPPORTED("998", "请求方法不支持"), SYSTEM_ERROR("999", "系统异常");
    private String code;
    private String message;

    WechatOpenApiErrorEnum(String code, String message)
    {
        this.code = code;
        this.message = message;
    }


    public String getCode()
    {
        return code;
    }

    public String getMessage()
    {
        return message;
    }

    public static WechatOpenApiErrorEnum fromCode(String code)
    {
        if ( code == null )
        {
            return null;
        }

        for (WechatOpenApiErrorEnum financialErrorEnum : values())
        {
            if ( financialErrorEnum.getCode().equals(code) )
            {
                return financialErrorEnum;
            }
        }
        return null;
    }
}
