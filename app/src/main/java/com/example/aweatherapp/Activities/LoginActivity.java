package com.example.aweatherapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.aweatherapp.Database.DatabaseHelper;
import com.example.aweatherapp.R;
import com.example.aweatherapp.databinding.ActivityLoginBinding;

public class  LoginActivity extends AppCompatActivity {

    //Fields
    ActivityLoginBinding binding;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Get the login binding
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        //get the root to display the login
        setContentView(binding.getRoot());

        dbHelper = new DatabaseHelper(this);

        //login button
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.loginEmail.getText().toString();
                String password = binding.loginPassword.getText().toString();

                if(email.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this, "Email and password cannot be blank", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean checkCreds = dbHelper.checkEmailAndPassword(email, password);

                    if(checkCreds == true){
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                       Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                       startActivity(intent);
                    }
                    else{
                        Toast.makeText(LoginActivity.this, "Email or password did not match, please retype them and try again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //for the link to sign up
        binding.signUpLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }
}