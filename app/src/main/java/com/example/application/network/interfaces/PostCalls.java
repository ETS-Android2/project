package com.example.application.network.interfaces;

import com.example.application.models.pojos.Post;
import com.example.application.models.pojos.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostCalls {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts/{id}")
    Call<Post> getPost(@Path("id") int id);

    @GET("user/{id}")
    Call<User> getUser(@Path("id") int id);

}
