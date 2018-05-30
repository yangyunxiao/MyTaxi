package com.dalimao.mytaxi;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpTest  {

    @Test
    public void httpPost(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");

        RequestBody body = RequestBody.create(mediaType,"{\"name\":\"xiao\"}");

        Request request = new Request.Builder()
                .url("http://httpbin.org/post")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);

        try {
            Response  response = call.execute();
            System.out.println("response : " + response.body().string() );

        } catch (IOException e) {
            e.printStackTrace();
        }

//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                System.out.println("onResponse");
//
//                System.out.println("response : " + response.body().string() );
//            }
//        });

    }

    @Test
    public void testInterceptor(){

        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {

                long startTime = System.currentTimeMillis();
                Request request = chain.request();

                Response response = chain.proceed(request);

                long endTime = System.currentTimeMillis();

                System.out.println("time is "+ (endTime - startTime));

                return response;

            }
        };

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        MediaType mediaType = MediaType.parse("application/json;charset=utf-8");

        RequestBody body = RequestBody.create(mediaType,"{\"name\":\"xiao\"}");

        Request request = new Request.Builder()
                .url("http://httpbin.org/post")
                .post(body)
                .build();

        Call call = okHttpClient.newCall(request);

        try {
            Response  response = call.execute();
            System.out.println("response : " + response.body().string() );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void testCache(){

        Cache cache = new Cache(new File("cache.cache"),1024*1024);

        OkHttpClient client = new OkHttpClient.Builder().cache(cache).build();

        Request request = new Request.Builder().url("http://httpbin.org/get").build();

        try {
            Response response = client.newCall(request).execute();

            Response cacheControl = response.cacheResponse();

            Response networkResponse = response.networkResponse();

            if (cacheControl!=null){

                System.out.println("response is from cache");
            }
            if (networkResponse !=null){

                System.out.println("response is from network");
            }

            System.out.println(response.code() + response.body().string());

        } catch (IOException e) {

            e.printStackTrace();

        }
    }
}
