package com.example.smartdrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import net.daum.mf.map.api.MapView;

public class MapGas extends AppCompatActivity {
    Button chatbotBt, parkingBt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapgas_miniinfo);


        //지도(뷰 추가): 메인 뷰 그룹에 코드를 통해 add 해야 함
        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        chatbotBt = (Button)findViewById(R.id.chatbot);
        parkingBt = (Button)findViewById(R.id.parking);

        chatbotBt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //매인(챗봇) -> 챗봇 화면 넘어갈 때 쓰일 인텐트
                startActivity(new Intent(MapGas.this, MainActivity.class));
            }
        });
        parkingBt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //매인(챗봇) -> 지도(주유소) 화면 넘어갈 때 쓰일 인텐트
                startActivity(new Intent(MapGas.this, MapParking.class));
            }
        });
    }
}
