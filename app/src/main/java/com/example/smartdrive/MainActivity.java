package com.example.smartdrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent= new Intent(MainActivity.this, MapParking.class);
        //매인(챗봇) -> 지도 화면 넘어갈 때 쓰일 인텐트

        //startActivity(intent);
    }
}