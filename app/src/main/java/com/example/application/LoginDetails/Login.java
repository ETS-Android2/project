package com.example.application.LoginDetails;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.application.Register;
import com.example.application.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        binding.next.signin.setOnClickListener(view -> startActivity(new Intent(Login.this, Register.class)));

        
        
        binding.next.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });


    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    private void register() {

        if(validate()){

            String name = binding.next.name.toString().trim();
            String email = binding.next.email.toString().trim();
            String pass = binding.next.password.toString().trim();

            mAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                startActivity(new Intent(Login.this, Register.class));

                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(Login.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                        }
                    });
        }

    }

    private boolean validate() {

        if(TextUtils.isEmpty( binding.next.email.getText().toString().trim())){
            binding.next.email.setError("This field is required"  );
            return  false;
        }
        if( TextUtils.isEmpty( binding.next.name.getText().toString().trim())){
            binding.next.name.setError("This field is required"  );
            return  false;
        }
        if (TextUtils.isEmpty( binding.next.password.getText().toString().trim())){
            binding.next.password.setError("This field is required");
            return  false;
        }
        return true;
    }
}