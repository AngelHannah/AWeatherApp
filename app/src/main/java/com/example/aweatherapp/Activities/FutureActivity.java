package com.example.aweatherapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aweatherapp.Adapters.FutureAdapter;
import com.example.aweatherapp.Domains.Future;
import com.example.aweatherapp.R;

import java.util.ArrayList;

public class FutureActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterTommorow;
    public RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_future);

        initRecyclerView();
        setVariable();

    }

    private void setVariable() {
        ImageView backToDashBtn = findViewById(R.id.backToDashBtn);
        backToDashBtn.setOnClickListener(v -> startActivity(new Intent(FutureActivity.this, MainActivity.class)));
    }

    private void initRecyclerView() {
        ArrayList<Future> items = new ArrayList<>();

        items.add(new Future("Tuesday", "cloudy", "Cloudy",69,46));
        items.add(new Future("Wednesday", "sunny", "Sunny",70,48));
        items.add(new Future("Thursday", "windy", "Windy",68,45));
        items.add(new Future("Friday", "cloudy_sunny", "Cloudy And Sunny", 66,42));
        items.add(new Future("Saturday", "cloudy", "Cloudy",65,40));
        items.add(new Future("Sunday", "rainy", "Rainy",67,44));

        recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterTommorow = new FutureAdapter(items);
        recyclerView.setAdapter(adapterTommorow);

    }
}