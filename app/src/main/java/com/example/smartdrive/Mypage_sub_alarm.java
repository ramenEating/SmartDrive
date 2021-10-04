package com.example.smartdrive;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class Mypage_sub_alarm extends AppCompatActivity {

    private Toolbar mToolbar; //툴바 설정
    private TextView mTextView; //잔여석 수 설정 텍스트
    private Spinner mSpinner; //잔여석 수 설정 스피너
    private Switch mSwitch; //잔여석 알림 switch

    String[] spinnerList = {"1","2","3","4","5","6","7","8","9",
            "10","11","12","13","14","15","16","17","18","19","20"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_sub_alarm_ui);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTextView = (TextView)findViewById(R.id.textview1);
        mSpinner = (Spinner)findViewById(R.id.spinner);
        mSwitch = (Switch)findViewById(R.id.switch1);

        //intent 받기
        Intent intent = getIntent();
        String title = intent.getExtras().getString("key1");

        //툴바 설정
        mToolbar.setTitle(title); //타이틀 제목
        mToolbar.setTitleTextColor(Color.BLACK); //글자 색상
        setSupportActionBar(mToolbar); //툴바를 액션바로 사용함을 선언

        //잔여석 수 설정 spinner버튼
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerList);
                    //spinner 디자인과, 값으로 들어갈 list 설덩

        mSpinner.setAdapter(adapter);

        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(getApplicationContext(), spinnerList[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "선택되지 않았습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        //잔여석 알림 switch 버튼
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean check) {
                if (check){ //잔여석 알림이 On인 경우
                    Toast.makeText(getApplicationContext(),
                            "잔여석 알림 On", Toast.LENGTH_SHORT).show();
                } else {//잔여석 알림이 Off인 경우
                    Toast.makeText(getApplicationContext(),
                            "잔여석 알림 Off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 어댑터 연결
        //mMypageAdapter = new MypageAdapter(this, listMypage);
        //mListView.setAdapter(mMypageAdapter);
    }

}
