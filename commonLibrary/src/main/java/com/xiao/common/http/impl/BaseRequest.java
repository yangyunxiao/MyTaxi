package com.xiao.common.http.impl;

import com.google.gson.Gson;
import com.xiao.common.http.IRequest;

import java.util.HashMap;
import java.util.Map;

public class BaseRequest implements IRequest {

    private String method = POST;

    private String url;

    private Map<String, String> mHeader;

    private Map<String, Object> mBody;

    BaseRequest(String url) {
        this.url = url;
        mHeader = new HashMap<>();
        mBody = new HashMap<>();

        mHeader.put("Application-Id", "myTaxiID");
        mHeader.put("API-Key", "myTaxiKey");
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public void setHeader(String key, String value) {

        this.mHeader.put(key, value);
    }

    @Override
    public void setBody(String key, String value) {

        this.mBody.put(key, value);
    }

    @Override
    public Map<String, String> getHeader() {
        return mHeader;
    }

    @Override
    public String getBody() {
        if (mBody.size() > 0) {
            return new Gson().toJson(mBody, HashMap.class);
        }
        return "{}";
    }

    @Override
    public String getUrl() {
        if (GET.equals(method)) {
            for (String key :
                    mBody.keySet()) {
                url = url.replace("${" + key + "}", mBody.get(key).toString());
            }
        }
        return url;
    }
}
