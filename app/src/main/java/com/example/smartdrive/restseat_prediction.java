package com.example.smartdrive;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;


import androidx.annotation.Nullable;

public class restseat_prediction extends AppCompatActivity {
    final static String TAG = "CROCUS";
    float prevX, prevY;

    ImageView mImageView = findViewById(R.id.talk_bubble2);

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
