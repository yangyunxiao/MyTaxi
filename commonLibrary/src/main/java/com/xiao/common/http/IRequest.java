package com.xiao.common.http;

import java.util.Map;

/**
 * 抽取请求基类  必备
 * 请求方法method
 * 头部  header
 * 请求体  body
 * 地址   url
 */
public interface IRequest {

    String POST = "POST";

    String GET = "GET";

    /**
     * 设置请求方式
     */
    void setMethod(String method);

    /**
     * 添加请求头
     */
    void setHeader(String key, String value);

    /**
     * 添加请求参数
     */
    void setBody(String key, String value);

    Map<String, String> getHeader();

    String getBody();

    String getUrl();

}
