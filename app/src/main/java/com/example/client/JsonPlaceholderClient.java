package com.example.client;

import com.example.service.JsonPlaceholderService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JsonPlaceholderClient {
    private static Retrofit retrofit;

    private static Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static JsonPlaceholderService getService() {
        return getRetrofit().create(JsonPlaceholderService.class);
    }

}
