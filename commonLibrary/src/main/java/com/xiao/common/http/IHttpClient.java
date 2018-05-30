package com.xiao.common.http;

public interface IHttpClient {

    IResponse get(IRequest request, boolean forceRefresh);

    IResponse post(IRequest request, boolean forceRefresh);
}
