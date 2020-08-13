package com.example.client;

import com.example.service.MyReceiptsService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyReceiptsClient {
    private static Retrofit retrofit;

    private static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://192.168.0.15:8080/api/test/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static MyReceiptsService getService() {
        return getRetrofit().create(MyReceiptsService.class);
    }

}
