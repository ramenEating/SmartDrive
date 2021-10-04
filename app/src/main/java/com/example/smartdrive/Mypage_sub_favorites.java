package com.example.smartdrive;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Mypage_sub_favorites extends AppCompatActivity {

    private ArrayList<String> listMypage;
    private ListView mListView;
    private MypageAdapter mMypageAdapter;
    private Context mContext;
    private Toolbar mToolbar; //툴바 설정
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_sub_favorite_ui);

       // mListView = (ListView) findViewById(R.id.listView);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTextView = (TextView)findViewById(R.id.tv1);

        Intent intent = getIntent();
        String title = intent.getExtras().getString("key0");

        //툴바 설정
        mToolbar.setTitle(title); //타이틀 제목
        mToolbar.setTitleTextColor(Color.BLACK); //글자 색상
       setSupportActionBar(mToolbar); //툴바를 액션바로 사용함을 선언

        mTextView.setText("의정부역 주차장");

        // 어댑터 연결
        //mMypageAdapter = new MypageAdapter(this, listMypage);
        //mListView.setAdapter(mMypageAdapter);
    }

}
