package com.example.smartdrive;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class Mypage extends AppCompatActivity {


    private ArrayList<String> array_mypage;
    private  ListView mListView;
    private MypageAdapter mMypageAdapter;
    private Context mContext;
    private Toolbar mToolbar; //툴바 설정
    final static int a=0;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_ui);

        mListView = (ListView) findViewById(R.id.mypageList);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        //툴바 설정
        mToolbar.setTitle("마이페이지"); //타이틀 제목
        mToolbar.setTitleTextColor(Color.BLACK); //글자 색상
        setSupportActionBar(mToolbar); //툴바를 액션바로 사용함을 선언


        //데이터 생성
        array_mypage = new ArrayList<>();
        array_mypage.add("즐겨찾기 설정");
        array_mypage.add("알림 설정");
        array_mypage.add("계정 설정");
        array_mypage.add("약관 및 정책 추가");
        array_mypage.add("어플리케이션 정보");
        array_mypage.add("어플 사용 가이드북");

        // 어댑터 연결
        mMypageAdapter = new MypageAdapter(Mypage.this, array_mypage);
        mListView.setAdapter(mMypageAdapter);

        //리스트 클릭시 넘어가는 화면
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View v, int position, long l) {
                                    //클릭된 항목의 부모뷰인 어댑터 뷰, 사용자 클릭한 항목에 해당하는 뷰,
                                    //선택된 항목 위치, id=position과 동일
                switch (position)
                {
                    case 0:
                        intent = new Intent(getApplicationContext(), Mypage_sub_favorites.class);
                        intent.putExtra("key0","즐겨찾기" );
                    case 1:
                        intent = new Intent(getApplicationContext(), Mypage_sub_alarm.class);
                        intent.putExtra("key1","알림설정" );
                    case 2:
                        intent = new Intent(getApplicationContext(), Mypage_sub_favorites.class);
                        intent.putExtra("key2","계정설정" );
                    case 3:
                        intent = new Intent(getApplicationContext(), Mypage_sub_favorites.class);
                        intent.putExtra("key3","약관 및 정책" );
                    case 4:
                        intent = new Intent(getApplicationContext(), Mypage_sub_favorites.class);
                        intent.putExtra("key4","어플리케이션 정보" );
                    case 5:
                        intent = new Intent(getApplicationContext(), Mypage_sub_favorites.class);
                        intent.putExtra("key5","어플 사용 가이드북" );
                }
                startActivity(intent);
            }
        });
    }



}
