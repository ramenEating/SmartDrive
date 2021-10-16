package com.example.smartdrive;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button parkingBt, gasBt, mypageBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parkingBt = (Button)findViewById(R.id.parking);
        gasBt = (Button)findViewById(R.id.gas);
        mypageBt = (Button)findViewById(R.id.mypage);

        parkingBt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //매인(챗봇) -> 지도(주차장) 화면 넘어갈 때 쓰일 인텐트
                startActivity(new Intent(MainActivity.this, MapParking.class));
            }
        });

        gasBt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //매인(챗봇) -> 지도(주유소) 화면 넘어갈 때 쓰일 인텐트
                startActivity(new Intent(MainActivity.this, MapGas.class));
            }
        });

        mypageBt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //매인(챗봇) -> 마이페이지 화면 넘어갈 때 쓰일 인텐트
                startActivity(new Intent(MainActivity.this, Mypage.class));
            }
        });

    }
}