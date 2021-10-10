package com.example.smartdrive;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import net.daum.mf.map.api.MapView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parkinginfo extends AppCompatActivity {
    ExpandableListAdapter listViewAdapter;
    ExpandableListView expandableListView;
    List<String> chapterList; //상위 리스트
    HashMap<String, List<String>> topicList; //하위 리스트
    private Toolbar mToolbar; //툴바 설정

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parkinginfo);

        //툴바 설정
        mToolbar.setTitle("마이페이지"); //타이틀 제목
        mToolbar.setTitleTextColor(Color.BLACK); //글자 색상
        setSupportActionBar(mToolbar); //툴바를 액션바로 사용함을 선언


        expandableListView = (ExpandableListView)findViewById(R.id.listView);
        showList();
        listViewAdapter = new ExpandableListViewAdapter(this, chapterList, topicList);
        expandableListView.setAdapter(listViewAdapter);
    }

    private void showList() {
        chapterList = new ArrayList<String>();
        topicList = new HashMap<String, List<String>>();

        //주차요금
        chapterList.add("주차 요금");
        List<String> topic1 = new ArrayList<>();
        topic1.add("10분 ㅇㅇㅇ\n30분 ㅇㅇㅇ\n1시간 ㅇㅇㅇ");
        topicList.put(chapterList.get(0), topic1);

        //주차장이용시간
        chapterList.add("주차장 이용 시간");
        List<String> topic2 = new ArrayList<>();
        topic2.add("ㅁㅁㅁㅁㅁ\nㅇㅇㅇㅇㅇㅇ");
        topicList.put(chapterList.get(1), topic2);

    }
}