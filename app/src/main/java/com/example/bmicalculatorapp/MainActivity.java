package com.example.bmicalculatorapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.math.RoundingMode;
import java.text.DecimalFormat;

import static java.lang.Float.parseFloat;

public class MainActivity extends AppCompatActivity {

    Button btnCalculate;
    EditText etWeight;
    EditText etHeight;
    Spinner spnUnit ;
    String[] units= {"cm","ft","m"};
    String selectedUnit ;
    float BMIvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Find Views By ID
        btnCalculate = findViewById(R.id.btnCalculate);
        etWeight = findViewById(R.id.etWeight);
        etHeight = findViewById(R.id.etHeight);
        spnUnit = findViewById(R.id.spnUnit);

        //Set Button Color Programatically
        btnCalculate.setBackgroundColor(getResources().getColor(R.color.BlueJay));
        spnUnit.getBackground().setColorFilter(getResources().getColor(R.color.black), PorterDuff.Mode.SRC_ATOP);

        //Array Adapter for spinner
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnUnit.setAdapter(adapter);
        spnUnit.setSelection(0);
        spnUnit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedUnit = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(),selectedUnit,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spnUnit.setSelected(true);
                spnUnit.setSelection(0);
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String value1 = etWeight.getText().toString();
                String value2 = etHeight.getText().toString();
                if(value1.equals("")||value2.equals("")) {
                    Toast.makeText(getApplicationContext(), "Empty field not allowed", Toast.LENGTH_SHORT).show();
                }else {
                    float weight = parseFloat(value1);
                    float height = parseFloat(value2);
                    if(selectedUnit==units[0]) {
                        float heightInM = height / 100;
                        BMIvalue = weight / (heightInM * heightInM);
                    }else if(selectedUnit==units[1]){
                        float heightInM = (float) (height / 3.281);
                        BMIvalue = weight / (heightInM * heightInM);
                    }
                    else if(selectedUnit==units[2]){
                        BMIvalue = weight / (height * height);
                    }
                    //round off the bmi value to 2 decimal places
                    DecimalFormat df = new DecimalFormat("0.00");
                    df.setRoundingMode(RoundingMode.UP);
                    String BMI=df.format(BMIvalue);
                    Toast.makeText(getApplicationContext(),"BMI : "+BMI, Toast.LENGTH_SHORT).show();
                    //pass bmi value to different activities
                    //condition for underweight
                    if(BMIvalue<18.5){
                        Intent intent = new Intent(MainActivity.this,UnderActivity.class);
                        intent.putExtra("gotBMI",BMI);
                        if(BMIvalue<16){
                            String info = "You Are Severely Underweight";
                            intent.putExtra("showInfo",info);
                        }else if(BMIvalue>=16 && BMIvalue<18.5){
                            String info = "You Are Underweight";
                            intent.putExtra("showInfo",info);
                        }
                        startActivity(intent);
                    //condition for Normal weight
                    }else if(BMIvalue>=18.5 && BMIvalue<25){
                        Intent intent = new Intent(MainActivity.this,NormalActivity.class);
                        intent.putExtra("gotBMI",BMI);
                        startActivity(intent);
                    //condition for over weight
                    }else if(BMIvalue>=25.0){
                        Intent intent = new Intent(MainActivity.this,OverActivity.class);
                        intent.putExtra("gotBMI",BMI);
                        if (BMIvalue>= 25 && BMIvalue<30) {
                            String info = "You Are Overweight";
                            intent.putExtra("showInfo",info);
                        }else if(BMIvalue>= 30 && BMIvalue<35){
                            String info = "You Are Moderately Obese";
                            intent.putExtra("showInfo",info);
                        }else if(BMIvalue>= 35 && BMIvalue<40){
                            String info = "You Are Severely Obese";
                            intent.putExtra("showInfo",info);
                        }else if(BMIvalue>=40){
                            String info = "You Are Morbidly Obese";
                            intent.putExtra("showInfo",info);
                        }
                        startActivity(intent);
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.mnuChart :
                Intent intent = new Intent(MainActivity.this,ChartActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}