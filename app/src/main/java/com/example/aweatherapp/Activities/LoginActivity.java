package com.example.aweatherapp.Activities;

import android.os.Bundle;

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
        //get the root to display the login and its view
        setContentView(binding.getRoot());

    }
}