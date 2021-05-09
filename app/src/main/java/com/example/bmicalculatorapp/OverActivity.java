package com.example.bmicalculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OverActivity extends AppCompatActivity {

    TextView tvBMI;
    TextView tvOverWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_over);

        tvBMI = findViewById(R.id.tvBMI);
        tvOverWeight = findViewById(R.id.tvOverWeight);

        Intent intent = getIntent();
        String gotBMI = intent.getStringExtra("gotBMI");
        String gotValue = intent.getStringExtra("showInfo");
        tvOverWeight.setText(gotValue);
        tvBMI.setText(gotBMI);
    }
}