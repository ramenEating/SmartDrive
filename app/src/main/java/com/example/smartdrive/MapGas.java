package com.example.smartdrive;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

//import com.perdro.library.AutoPermissions;
//import com.pedro.library.AutoPermissionsListener;


public class MapGas extends AppCompatActivity  {

    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    Button chatbotBt, parkingBt, nowlocal;
    private MarkerOptions myLocationMarker;
    private Object AutoPermissions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapgas_miniinfo);

        chatbotBt = (Button)findViewById(R.id.chatbot);
        parkingBt = (Button)findViewById(R.id.parking);
        nowlocal = (Button)findViewById(R.id.nowlocal);

        /*
        // 권한 id 가져오기
        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission2 = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);

        //권한 열려있는지 확인
        if (permission == PackageManager.PERMISSION_DENIED || permission2 == PackageManager.PERMISSION_DENIED) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //권한 체크
                requestPermissions(new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE}, 1000);
            }
            return;
        }

*/

        //googleMap 객체가 준비될때 실행될 콜백 메소드
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map_view);
        //  mapFragment.getMapAsync(this); //메인 스레드에서 호출되어야 onMapReady 콜백 실행

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                Log.d("MAp", "지도 준비됨");
                mMap = googleMap;

            }
        });

        try {
            MapsInitializer.initialize(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        nowlocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startLocationService();
            }
        });

        //AutoPermissions.Companion.loadAllPermission(this, 101);


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
                // startActivity(new Intent(MapGas.this, MapParking.class));
            }
        });
    }

    /*
    //권한 체크 이후 로직
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grandResults) {
        //READ_PHONE_STATE의 권한 체크 결과
        if (requestCode == 1000) {

            //모든 퍼미션 허용 체크
            boolean check_result = false;
            for (int result : grandResults) {
                if(result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }
            //권한 체크 없으면 종료
            if(check_result == true) {

            } else {
                finish();
            }
        }
    }
*/


    public void startLocationService() {
        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            Location location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();
                String message = "최근 위치 -> Latitude : " + latitude + "\nLongitude:" + longitude;

                Log.d("Map", message);
            }

            GPSListener gpsListener = new GPSListener();
            long minTime = 10000;
            float minDistance = 0;

            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER,
                    minTime, minDistance, (LocationListener) gpsListener);

            Toast.makeText(getApplicationContext(), "내 위치확인 요청함",
                    Toast.LENGTH_SHORT).show();

        } catch(SecurityException e) {
            e.printStackTrace();
        }
    }

    class GPSListener implements LocationListener {
        public void onLocationChanged(Location location) {
            Double latitude = location.getLatitude();
            Double longitude = location.getLongitude();

            String message = "내 위치 -> Latitude : "+ latitude + "\nLongitude:"+ longitude;
            Log.d("Map", message);

            showCurrentLocation(latitude, longitude);
        }

        public void onProviderDisabled(String provider) { }

        public void onProviderEnabled(String provider) { }

        public void onStatusChanged(String provider, int status, Bundle extras) { }
    }

    private void showCurrentLocation(Double latitude, Double longitude) {
        LatLng curPoint = new LatLng(latitude, longitude);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));

        showMyLocationMarker(curPoint);
    }

    private void showMyLocationMarker(LatLng curPoint) {
        if (myLocationMarker == null) {
            myLocationMarker = new MarkerOptions();
            myLocationMarker.position(curPoint);
            myLocationMarker.title("● 내 위치\n");
            myLocationMarker.snippet("● GPS로 확인한 위치");
            myLocationMarker.icon(BitmapDescriptorFactory.fromResource(R.drawable.mylocation));
            mMap.addMarker(myLocationMarker);
            mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        } else {
            myLocationMarker.position(curPoint);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // AutoPermissions.Companion.parsePermissions(this, requestCode, permissions, this);
    }

/*
    @Override
    public void onDenied(int requestCode, String[] permissions) {
        Toast.makeText(this, "permissions denied : " + permissions.length, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onGranted(int requestCode, String[] permissions) {
        Toast.makeText(this, "permissions granted : " + permissions.length, Toast.LENGTH_LONG).show();
    }
*/

    /*

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

        /*
        //moveCamera 카메라를 지정한 위도, 경도 이동
        mMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10)); //지정한 단계로 카메라 줌 조정. 1단계=세계지도 수준. 숫자 클수록 상세지도

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 10));



    }
    */


}