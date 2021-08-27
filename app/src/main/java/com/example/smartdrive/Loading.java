package com.example.smartdrive;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Loading extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_ui);


        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public   void run() {
                 startActivity(new Intent(Loading.this, Login.class));
                finish();
            }
        }, 3000);
    }
}
