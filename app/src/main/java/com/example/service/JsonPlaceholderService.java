package com.example.service;

import com.example.model.Post;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceholderService {

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") Integer id);

}
