package com.example.android_service;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private final String LOG_KEY = "LOG_KEY@" + MainActivity.class.getSimpleName();
    private Button startButton, stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.startButton = findViewById(R.id.startServiceBT);
        this.stopButton = findViewById(R.id.stopServiceBT);

        Intent intent = new Intent(this, MyService.class);
        this.startButton.setOnClickListener(v -> {
            startService(intent);
        });
        this.stopButton.setOnClickListener(v -> {
            stopService(intent);
        });
    }
}