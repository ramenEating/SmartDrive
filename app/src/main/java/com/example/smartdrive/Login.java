package com.example.smartdrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    Button nonmember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);

        nonmember = (Button)findViewById(R.id.nonmemberLogin);

        nonmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그인 -> 메인 화면으로 넘어갈 때 쓰일 인텐트 선언
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });
    }
}
