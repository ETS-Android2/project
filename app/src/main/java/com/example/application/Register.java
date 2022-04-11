package com.example.application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.application.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());





        binding.layLogin.cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validate()){

                    String email = binding.layLogin.editTextEmail.toString().trim();
                    String pass  = binding.layLogin.editTextPassword.toString().trim();

                    mAuth.signInWithEmailAndPassword(email, pass)
                            .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        FirebaseUser user = mAuth.getCurrentUser();

                                        startActivity(new Intent(Register.this,MainActivity.class));
                                    } else {
                                        // If sign in fails, display a message to the user.

                                        binding.layLogin.editTextPassword.setError("Invalid pass");
                                        binding.layLogin.editTextEmail.setError("Invalid email");
                                        binding.layLogin.editTextEmail.getText().clear();
                                        binding.layLogin.editTextPassword.getText().clear();

                                        Toast.makeText(Register.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }


                                }
                            });


                }

            }
        });



    }

    private boolean validate() {

        if(TextUtils.isEmpty( binding.layLogin.editTextEmail.getText().toString().trim())){
            binding.layLogin.editTextEmail.setError("This field is required"  );
            return  false;
        }
        if( TextUtils.isEmpty( binding.layLogin.editTextPassword.getText().toString().trim())){
            binding.layLogin.editTextPassword.setError("This field is required"  );
            return  false;
        }
        return true;
    }
}