package com.example.retrofit.jsonPlacheholderApi;

import com.google.gson.annotations.SerializedName;

public class Post {
    
    Integer userId;

    Integer id;

    String title;

//    @SerializedName("body")
//    String text;

    public Integer getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

//    public String getText() {
//        return text;
//    }
}
