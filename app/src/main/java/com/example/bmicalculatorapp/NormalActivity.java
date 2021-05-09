package com.example.bmicalculatorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NormalActivity extends AppCompatActivity {

    TextView tvBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);

        //find view by id
        tvBMI = findViewById(R.id.tvBMI);
        //get values from intent
        Intent intent = getIntent();
        String gotBMI = intent.getStringExtra("gotBMI");
        //set text
        tvBMI.setText(gotBMI);
    }
}