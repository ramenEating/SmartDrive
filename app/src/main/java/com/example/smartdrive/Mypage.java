package com.example.smartdrive;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Mypage extends AppCompatActivity {


    private ArrayList<String> array_mypage;
    private  ListView mListView;
    private MypageAdapter mMypageAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_ui);
        this.mContext = getApplicationContext();

        mListView = (ListView) findViewById(R.id.mypageList);

        //데이터 생성
        array_mypage = new ArrayList<>();
        array_mypage.add("즐겨찾기 설정");
        array_mypage.add("알림 설정");
        array_mypage.add("계정 설정");
        array_mypage.add("약관 및 정책 추가");
        array_mypage.add("어플리케이션 정보");
        array_mypage.add("어플 사용 가이드북");

        // 아답터 연결

        mMypageAdapter = new MypageAdapter(mContext, array_mypage);
        mListView.setAdapter(mMypageAdapter);
    }

}
