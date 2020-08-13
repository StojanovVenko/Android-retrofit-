package com.example.retrofit.jsonPlacheholderApi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyApiService {

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") Integer id);

}
