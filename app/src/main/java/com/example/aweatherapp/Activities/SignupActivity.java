package com.example.aweatherapp.Activities;

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
import com.example.aweatherapp.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    ActivitySignupBinding binding;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        dbHelper = new DatabaseHelper(this);

        binding.submitSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = binding.signUpEmail.getText().toString();
                String password = binding.signUpPassword.getText().toString();
                String confirmPassword = binding.signUpConfirmPassword.getText().toString();
                
                //Check if any field is empty
                if(email.equals("") || password.equals("") || confirmPassword.equals("")){
                    Toast.makeText(SignupActivity.this, "Please complete each field", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Check if passwords are the same
                    if(password.equals(confirmPassword)){
                          
                        //Check for email in the db
                        Boolean checkEmail = dbHelper.checkEmail(email);

                        //If the email isnt already there then try to insert the new email/password combo
                        if(checkEmail == false){
                            //Add the new user to the db
                            Boolean insert =  dbHelper.addUser(email, password);
                        }
                    }
                }
            }
        });

    }
}