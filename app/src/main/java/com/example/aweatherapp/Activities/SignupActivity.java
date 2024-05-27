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

        binding.submitSignUpBtn.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                String email = binding.signUpEmail.getText().toString();
                String password = binding.signUpPassword.getText().toString();
                String confirmPassword = binding.signUpConfirmPassword.getText().toString();
                
                //Check if any field is empty
                if(email.equals("") || password.equals("") || confirmPassword.equals("")){
                    //Lets the user know all fields need to be filled in
                    Toast.makeText(SignupActivity.this, "Please complete each field", Toast.LENGTH_SHORT).show();
                }
                else {
                    //Check if passwords are the same
                    if(password.equals(confirmPassword)){
                          
                        //Check for email in the db - does it exist already?
                        Boolean checkEmail = dbHelper.checkEmail(email);

                        //If the email isnt already there then try to insert the new email/password combo
                        if(checkEmail == false){
                            //Add the new user to the db - returns bool
                            Boolean insert =  dbHelper.addUser(email, password);

                            if(insert == true){
                                //Show success message
                                Toast.makeText(SignupActivity.this, "Successfully Signed Up!", Toast.LENGTH_SHORT).show();

                                //Then send them to the login page
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(SignupActivity.this, "Unsuccessful sign up, please try again!", Toast.LENGTH_SHORT).show();
                            }//End of insert if/else

                        }
                        else{
                            Toast.makeText(SignupActivity.this, "This email is already in use, please login instead!", Toast.LENGTH_SHORT).show();

                        }//End of checkEmail if/else
                    }
                    else{
                        Toast.makeText(SignupActivity.this, "Passwords don't match, please retype them and try again!", Toast.LENGTH_SHORT).show();

                    }//End of the empty fields if/else
                }
            }
        });//End of click listener

        binding.logInLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}