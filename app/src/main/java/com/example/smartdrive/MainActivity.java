package com.example.smartdrive;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    final static String TAG = "CROCUS";
    float prevX, prevY;

    ImageView mImageView = findViewById(R.id.malpungsun_1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent= new Intent(MainActivity.this, MapParking.class);
        //매인(챗봇) -> 지도 화면 넘어갈 때 쓰일 인텐트

        //startActivity(intent);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        prevX = motionEvent.getX();
                        prevY = motionEvent.getY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        float dx = motionEvent.getX() - prevX;
                        float dy = motionEvent.getY() - prevY;
                        Log.v(TAG, "dx : " + dx + " dy :: " + dy);
                        view.setX(motionEvent.getX());
                        view.setY(motionEvent.getY());
                        break;

                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;

            }
        });
    }

}