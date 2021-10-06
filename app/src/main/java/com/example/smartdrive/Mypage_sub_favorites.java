package com.example.smartdrive;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class Mypage_sub_favorites extends AppCompatActivity {


    private ListView mListView;
    private Toolbar mToolbar; //툴바 설정
    private FavoriteAdapter adapter;

    private EditText edt_title;
    private EditText edt_sub;
    private Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_sub_favorite_ui);
        edt_title = (EditText) findViewById(R.id.edt_title);
        edt_sub = (EditText) findViewById(R.id.edt_sub);
        btn_add = (Button) findViewById(R.id.btn_add);
        mListView = (ListView) findViewById(R.id.listview);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        // 어댑터 연결
        adapter = new FavoriteAdapter(Mypage_sub_favorites.this);
        mListView.setAdapter(adapter);

        //인텐트값 받아오기
        Intent intent = getIntent();
        String title = intent.getExtras().getString("key0");

        //툴바 설정
        mToolbar.setTitle(title); //타이틀 제목
        mToolbar.setTitleTextColor(Color.BLACK); //글자 색상
       setSupportActionBar(mToolbar); //툴바를 액션바로 사용함을 선언


        // 데이터 추가하기
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapter.addItem(edt_title.getText().toString(), edt_sub.getText().toString());
                edt_title.setText("");
                edt_sub.setText("");

                adapter.notifyDataSetChanged();

            }

        });
    }

}
