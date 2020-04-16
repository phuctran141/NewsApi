package com.example.recyclerview_testapi.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkModule {

    private static Retrofit retrofit =null;

    private NetWorkModule(){

    }
    public static ApiService getInstance(){
        if (retrofit==null){
            retrofit = CreateRetrofit();
        }

        return retrofit.create(ApiService.class);
    }

    private static Retrofit CreateRetrofit() {
//        https://newsapi.org/v2/everything?q=bitcoin&from=2020-03-10&sortBy=publishedAt&apiKey=API_KEY

        OkHttpClient okHttpClient = new OkHttpClient.Builder()

                .connectTimeout(300, TimeUnit.SECONDS)
                .readTimeout(300,TimeUnit.SECONDS)
                .writeTimeout(300,TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();
        Gson gson =new GsonBuilder().disableHtmlEscaping().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://newsapi.org/v2/")

                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

}
