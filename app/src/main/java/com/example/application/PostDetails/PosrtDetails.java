package com.example.application.PostDetails;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.application.R;
import com.example.application.databinding.ActivityPosrtDetailsBinding;
import com.example.application.models.pojos.Post;
import com.example.application.models.pojos.User;
import com.example.application.network.instance.RetrofitInstance;
import com.example.application.network.interfaces.PostCalls;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PosrtDetails extends AppCompatActivity {
    public static PostCalls postCalls;
    Call<Post> post;
    Call<User> user;
    private static final String TAG = "PosrtDetails";
    private ActivityPosrtDetailsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPosrtDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int id = 0;
        int uid = 0;
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
        } else {
             uid = extras.getInt("uid");
             id = extras.getInt("id");
        }



        postCalls = RetrofitInstance.getApiClient();
        Log.d(TAG, " "+uid+" today" +id);
        loadInfo(id,uid);
    }

    private void loadInfo(int id , int uid) {

        post =  PosrtDetails.postCalls.getPost(id);

        post.clone().enqueue(new Callback<Post>() {
            @Override
            public void onResponse(@NonNull Call<Post> call, @NonNull Response<Post> response) {
                if(response.isSuccessful()){
                    Post p = response.body();
                    Log.d(TAG, "onResponse: "+p.getBody());

                    assert p != null;
                    binding.tvPostId.setText(String.valueOf(p.getId()));
                    binding.tvTitle.setText(p.getTitle());
                    binding.tvBody.setText(p.getBody());
                    callDetails(p.getUserId());

                }
            }

            @Override
            public void onFailure(@NonNull Call<Post> call, @NonNull Throwable t) {

            }
        });

    }

    private void callDetails(int Uid) {
        user = PosrtDetails.postCalls.getUser(Uid);

        user.clone().enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if(response.isSuccessful()){
                    User user = response.body();
                    assert user != null;
                    Log.d(TAG, "onResponse: "+user.getEmail());
                    binding.tvEmailLabel.setText(user.getEmail());
                    binding.tvUserName.setText(user.getName());
                    binding.tvUsername.setText(user.getUsername());
                    binding.tvWebsite.setText(user.getWebsite());
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {

            }
        });

    }
}