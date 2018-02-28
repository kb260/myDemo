package com.kb260.bxjy.api;

import com.orhanobut.logger.Logger;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author  KB260
 * Created on  2018/2/25
 */

public class Api {
    private static ApiService SERVICE = null;
    public static ApiService getDefault() {
        if (SERVICE == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Logger.d("HTTPS:"+message));
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    //.addNetworkInterceptor(new StethoInterceptor())//网络拦截器
                    .addInterceptor(loggingInterceptor)
                    .build();
            SERVICE = new Retrofit.Builder()
                    .baseUrl(Url.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build().create(ApiService.class);
        }
        return SERVICE;
    }
}