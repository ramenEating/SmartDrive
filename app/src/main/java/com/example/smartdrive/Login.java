package com.example.smartdrive;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);

        Intent intent = new Intent(Login.this, MainActivity.class);
        //로그인 -> 메인 화면으로 넘어갈 때 쓰일 인텐트 선언

        //startActivity(); -> 넘어가는 동작할 때 사용
    }
}
