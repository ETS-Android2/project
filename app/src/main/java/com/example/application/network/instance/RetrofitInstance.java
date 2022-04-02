package com.example.application.network.instance;

import com.example.application.models.pojos.Post;
import com.example.application.network.interfaces.PostCalls;

import retrofit2.Retrofit;

public class RetrofitInstance {
    private static final String BASE_URL ="http://chergeionline.com:8000";
    private static Retrofit retrofit = null;

    public static PostCalls getApiClient(){

        if(retrofit ==null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(PostCalls.class);
    }
}
