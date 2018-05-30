package com.xiao.common.http.impl;

import com.xiao.common.http.IHttpClient;
import com.xiao.common.http.IRequest;
import com.xiao.common.http.IResponse;

import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpClientImpl implements IHttpClient {

    OkHttpClient mClient = new OkHttpClient.Builder().build();

    @Override
    public IResponse get(IRequest request, boolean forceRefresh) {

        request.setMethod(IRequest.GET);

        Request.Builder requestBuilder = new Request.Builder();

        Map<String, String> header = request.getHeader();

        for (String key : header.keySet()) {
            requestBuilder.addHeader(key, header.get(key));

        }
        String url = request.getUrl();

        requestBuilder.url(url);

        Request okRequest = requestBuilder.build();

        return this.execute(okRequest);
    }

    @Override
    public IResponse post(IRequest request, boolean forceRefresh) {

        request.setMethod(IRequest.POST);
        Request.Builder requestBuilder = new Request.Builder();

        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");

        RequestBody requestBody = RequestBody.create(mediaType, request.getBody());

        requestBuilder.url(request.getUrl());

        Map<String, String> header = request.getHeader();

        for (String key : header.keySet()) {
            requestBuilder.addHeader(key, header.get(key));
        }
        requestBuilder.post(requestBody);
        Request okRequest = requestBuilder.build();
        return this.execute(okRequest);
    }

    private IResponse execute(Request request) {

        BaseResponse baseResponse = new BaseResponse();

        try {
            Response okResponse = mClient.newCall(request).execute();

            baseResponse.setCode(okResponse.code());

            String responseBody = okResponse.body().string();

            baseResponse.setData(responseBody);

        } catch (IOException e) {
            e.printStackTrace();
            baseResponse.setCode(BaseResponse.ERROR);
            baseResponse.setData(e.getMessage());
        }

        return baseResponse;
    }
}
