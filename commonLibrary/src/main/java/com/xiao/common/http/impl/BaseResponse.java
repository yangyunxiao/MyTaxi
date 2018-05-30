package com.xiao.common.http.impl;

import com.xiao.common.http.IResponse;

public class BaseResponse implements IResponse {

    public static final int ERROR = 999;

    private int code ;

    private String data;

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getData() {
        return this.data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(String data) {
        this.data = data;
    }
}
