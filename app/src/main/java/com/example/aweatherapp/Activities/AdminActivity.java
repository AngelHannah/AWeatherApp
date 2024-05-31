package com.example.aweatherapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aweatherapp.Adapters.UserAdapter;
import com.example.aweatherapp.Database.DatabaseHelper;
import com.example.aweatherapp.Domains.User;
import com.example.aweatherapp.R;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    //Fields
    private EditText origEmailET, emailET, passwordET;
    private Button updateBtn, deleteBtn;
    private RecyclerView.Adapter adapterUser;
    private RecyclerView recyclerView;

    private DatabaseHelper dbHelper = new DatabaseHelper(AdminActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //Initialize the fields
        origEmailET = findViewById(R.id.edit_origEmailET);
        emailET = findViewById(R.id.edit_EmailET);
        passwordET = findViewById(R.id.edit_PasswordET);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        //Onclick for update button
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get the data converted
                String origEmail = origEmailET.getText().toString();
                String newEmail = emailET.getText().toString();
                String password = passwordET.getText().toString();

                //Check to see if everything is filled out
                if(origEmail.isEmpty() || newEmail.isEmpty() || password.isEmpty()){
                    Toast.makeText(AdminActivity.this, "User Information Not Updated, All Fields Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    //Update the user record in the db
                    Boolean success = dbHelper.updateUser(origEmail, newEmail, password);
                    if(success){
                        Toast.makeText(AdminActivity.this, "User Information Updated", Toast.LENGTH_SHORT).show();
                        //Call this to refresh the list of users after the update
                        initUserRecyclerView();
                    }
                    else{
                        Toast.makeText(AdminActivity.this, "Update Not Successful", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        //Onclick for the delete button
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String origEmail = origEmailET.getText().toString();

                // Check if the email field is filled
                if (origEmail.isEmpty()) {
                    Toast.makeText(AdminActivity.this, "Please enter the original email", Toast.LENGTH_SHORT).show();
                } else {
                    // Delete the user record from the db
                    boolean success = dbHelper.deleteUser(origEmail);
                    if (success) {
                        Toast.makeText(AdminActivity.this, "User Deleted", Toast.LENGTH_SHORT).show();
                        // Refresh the list of users after the deletion
                        initUserRecyclerView();
                    } else {
                        Toast.makeText(AdminActivity.this, "Delete Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //This is the method call that initializes the recycler view with the data to display
        initUserRecyclerView();
    }

    //Intialize the recyclerview
    private void initUserRecyclerView(){
        //This line calls the displayUsers method which is the CRUD read method in the db helper. The resulting list of users is stored here.
        ArrayList<User> users = dbHelper.displayUsers();

        //Getting the connection to the recycler component in the UI
        recyclerView = findViewById(R.id.userRecyclerV);

        //Set the layout manager
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));

        //Adding the adapter in and giving it the users list
        adapterUser = new UserAdapter(users);
        //Set the adapter here
        recyclerView.setAdapter(adapterUser);
    }


}