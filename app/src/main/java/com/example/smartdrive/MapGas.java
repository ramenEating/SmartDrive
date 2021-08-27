package com.example.smartdrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import net.daum.mf.map.api.MapView;

public class MapGas extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Button chatbotBt, parkingBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapgas_miniinfo);

        chatbotBt = (Button)findViewById(R.id.chatbot);
        parkingBt = (Button)findViewById(R.id.parking);

        //googleMap 객체가 준비될때 실행될 콜백 메소드
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map_view);
        mapFragment.getMapAsync(this); //메인 스레드에서 호출되어야 onMapReady 콜백 실행

/*
        //지도(뷰 추가): 메인 뷰 그룹에 코드를 통해 add 해야 함
        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);
*/


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

    //맵이 사용할 준비 되면 (NULL이 아닌 googleMap 객체를 파라미터로 제공해 줄 수 있을때) 호출
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        //MarkerOptions => 마커가 표시 될 위치, 타이틀, 클릭시 보여주는 간단 설명
        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL); //마커 위치
        markerOptions.title("서울");  //타이틀
        markerOptions.snippet("한국의 수도");  //클릭 시 보여주는 설명
        mMap.addMarker(markerOptions); //googleMap 객체에 추가해주면 지도에 표시

        //moveCamera 카메라를 지정한 위도, 경도 이동
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10)); //지정한 단계로 카메라 줌 조정. 1단계=세계지도 수준. 숫자 클수록 상세지도

        /* 78-79 줄 오작동시
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 10));
         */


    }
}
