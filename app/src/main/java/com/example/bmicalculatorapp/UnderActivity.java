package com.example.bmicalculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UnderActivity extends AppCompatActivity {

    TextView tvBMI;
    TextView tvUnderWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under);

        tvBMI = findViewById(R.id.tvBMI);
        tvUnderWeight = findViewById(R.id.tvUnderWeight);

        Intent intent = getIntent();
        String gotBMI = intent.getStringExtra("gotBMI");
        String gotValue = intent.getStringExtra("showInfo");
        tvUnderWeight.setText(gotValue);
        tvBMI.setText(gotBMI);
    }
}