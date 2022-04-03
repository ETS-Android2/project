package com.example.application.network.instance;

import com.example.application.constants.Const;
import com.example.application.models.pojos.Post;
import com.example.application.network.interfaces.PostCalls;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit = null;

    public static PostCalls getApiClient(){

        if(retrofit ==null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(Const.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(PostCalls.class);
    }
}
