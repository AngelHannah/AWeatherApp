package com.example.aweatherapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aweatherapp.Adapters.HourlyAdapter;
import com.example.aweatherapp.Domains.Hourly;
import com.example.aweatherapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    //Fields
    private RecyclerView.Adapter adapterHourly;
    private RecyclerView recyclerView;
    private TextView timeTxtV;
    private TextView dateTxtV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //Calling these to initialize the 7 day forecast button, recyclerview for hour by hour and setting current date and time
        initRecyclerView();
        setWeeklyForecastBtn();
        setDashboardDateTime();
    }

    //This handles setting up the function of the 7 day forecast button
    private void setWeeklyForecastBtn() {
        //Getting reference to the button
        TextView weeklyForecastBtn = findViewById(R.id.weeklyForecastBtn);

        //This launches the future forecast activity when you press the 7 day forecast button
        weeklyForecastBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, FutureActivity.class)));
    }

    //Handles initialization of the hour by hour forecast recycler view
    private void initRecyclerView() {
        ArrayList<Hourly> items = new ArrayList<>();

        //This is just some random data to display
        items.add(new Hourly("9 am", 43, "cloudy"));
        items.add(new Hourly(" 11 am", 49, "sunny"));
        items.add(new Hourly(" 12 pm", 53, "wind"));
        items.add(new Hourly(" 2 pm", 54, "rainy"));
        items.add(new Hourly("4 pm", 48, "storm"));

        //Get reference to recyclerview for hourly forecast
        recyclerView = findViewById(R.id.recyclerView1);
        //Setting the layout manager for the recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        adapterHourly = new HourlyAdapter(items);
        recyclerView.setAdapter(adapterHourly);
    }

    private void setDashboardDateTime(){
        //Get references
        timeTxtV = findViewById(R.id.dashboard_timeTxtV);
        dateTxtV = findViewById(R.id.dashboard_dateTxtV);

        //Variable for the date format
        //This is the date formatter
        SimpleDateFormat sDateFormat = new SimpleDateFormat("EEE, MMM d, yyyy");
        //And the time formatter
        SimpleDateFormat sTimeFormat = new SimpleDateFormat("h:m a");

        //Formatting date and time into new variables to pass along
        String currentDate = sDateFormat.format(new Date());
        String currentTime = sTimeFormat.format(new Date());

        //Setting the date and time here
        timeTxtV.setText(currentTime);
        dateTxtV.setText(currentDate);
    }
}