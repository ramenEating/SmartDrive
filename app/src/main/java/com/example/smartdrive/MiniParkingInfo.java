package com.example.smartdrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MiniParkingInfo extends AppCompatActivity {
    TextView minititle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.miniinfo_parking);

        minititle = (TextView)findViewById(R.id.miniinfo_title); //팝업창 주차장 이름
        //클릭 시 주차장 세부 정보창으로 넘어가게
        minititle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MiniParkingInfo.this, Parkinginfo.class));
            }
        });
    }
}
