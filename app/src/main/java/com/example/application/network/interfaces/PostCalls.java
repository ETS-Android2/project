package com.example.application.network.interfaces;

import com.example.application.models.pojos.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostCalls {

    @GET("posts")
    Call<List<Post>> getPost();


}
