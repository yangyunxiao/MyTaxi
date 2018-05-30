package com.xiao.common.http.impl;

import com.xiao.common.http.IHttpClient;
import com.xiao.common.http.IRequest;
import com.xiao.common.http.IResponse;
import com.xiao.common.http.api.API;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class OkHttpClientImplTest {

    private IHttpClient httpClient = new OkHttpClientImpl();

    @Before
    public void setUp() throws Exception {

        API.Config.setDebug(true);
    }

    @Test
    public void get() {

        IRequest request = new BaseRequest( API.Config.getDomain()+API.TEST_GET );
        request.setBody("uid","hahaha");
        IResponse response = httpClient.get(request,false);

        System.out.println(response.getData());
    }

    @Test
    public void post() {
    }
}