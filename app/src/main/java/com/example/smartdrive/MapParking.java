package com.example.smartdrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import net.daum.mf.map.api.MapView;

public class MapParking extends AppCompatActivity {
    Button searchBT, chatbotBt, gasBt;
    TextView minititle;
    private SearchView mSearchView;

    //숨겨진 페이지가 열렸는지 확인하는 변수
    boolean isPageOpen = false;
    Animation translateLeft, translateRight;
    LinearLayout hiddenPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapparking_miniinfo);

        minititle = (TextView)findViewById(R.id.miniinfo_title); //팝업창 주차장 이름
        //클릭 시 주차장 세부 정보창으로 넘어가게
        minititle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MapParking.this, parkinginfo.class));
            }
        });

        //지도(뷰 추가): 메인 뷰 그룹에 코드를 통해 add 해야 함
        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        chatbotBt = (Button)findViewById(R.id.chatbot);
        gasBt = (Button)findViewById(R.id.gas);

        chatbotBt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //매인(챗봇) -> 챗봇 화면 넘어갈 때 쓰일 인텐트
                startActivity(new Intent(MapParking.this, MainActivity.class));
            }
        });
        gasBt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //매인(챗봇) -> 지도(주유소) 화면 넘어갈 때 쓰일 인텐트
                startActivity(new Intent(MapParking.this, MapGas.class));
            }
        });

        //지도 실행 확인 위해 나머지 주석 처리
        /*
        hiddenPage = findViewById(R.id.hiddenPage);

        //anim 폴더의 애니메이션 가져와서 준비
        translateLeft = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRight = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        //페이지 슬라이딩 이벤트가 발생했을 때 애니메이션이 시작됐는지 종료됐는지 감지 가능
        SlidingPageAnimaionListener animListener = new SlidingPageAnimaionListener();

        translateLeft.setAnimationListener(animListener);
        translateRight.setAnimationListener(animListener);

        initView();
        handleIntent(getIntent());
        */
    }
    /*
    private class SlidingPageAnimaionListener implements Animation.AnimationListener{
        @Override
        public void onAnimationStart(Animation animation) {
        }
        public void onAnimationEnd(Animation animation){
            if(isPageOpen) hiddenPage.setVisibility(View.INVISIBLE);
        }
        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }
    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            mSearchView.setQuery(mSearchView.getQuery(), true);
            //use the query to search your data somehow
        }
    }
    private void initView() {
        //지도(뷰 추가): 메인 뷰 그룹에 코드를 통해 add 해야 함
        MapView mapView = new MapView(this);
        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        // 중심점 변경
        mapView.setMapCenterPoint(MapPoint.mapPointWithGeoCoord(37.53737528, 127.00557633), true);
        // 줌 레벨 변경
        mapView.setZoomLevel(7, true);
        // 중심점 변경 + 줌 레벨 변경
        mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(33.41, 126.52), 9, true);
        // 줌 인
        mapView.zoomIn(true);
        // 줌 아웃
        mapView.zoomOut(true);

        //마커 추가
        MapPoint MARKER_POINT = MapPoint.mapPointWithGeoCoord(37.54892296550104, 126.99089033876304);
        MapPOIItem marker = new MapPOIItem();
        marker.setItemName("기본 위치"); //클릭 시 나타나는 이름
        marker.setTag(0);
        marker.setMapPoint(MARKER_POINT);
        marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.
        marker.setSelectedMarkerType(MapPOIItem.MarkerType.RedPin); // 마커를 클릭했을때, 기본으로 제공하는 RedPin 마커 모양.
        marker.setDraggable(true);  // 핀을 꾹 눌러서 이동시킬 수 있다
        mapView.addPOIItem(marker); //추가한 마커를 지도에 추가시킨다

        MapView.POIItemEventListener mbpiel = new MapView.POIItemEventListener() {
            @Override
            public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
                //마커 클릭 시
                if(isPageOpen){
                    hiddenPage.startAnimation(translateRight);
                }else{
                    hiddenPage.setVisibility(View.VISIBLE);
                    hiddenPage.startAnimation(translateLeft);
                }
            }
            @Override
            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {
            }
            @Override
            public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {
            }
            @Override
            public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {
            }
        };

        //검색(서치뷰)
        mSearchView = findViewById(R.id.searchView); //searchView
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 검색 버튼이 눌러졌을 때 이벤트 처리
                mSearchView.setQuery(mSearchView.getQuery(), true);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                // 검색어가 변경되었을 때 이벤트 처리
                mSearchView.setQuery(mSearchView.getQuery(), false);
                return false;
            }
        });

        //개별 검색 버튼
        searchBT = (Button) findViewById(R.id.buttonSearch);
        searchBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //검색처리코드(???)
                mSearchView.setQuery(mSearchView.getQuery(), true);
            }
        });
    }
    */
}