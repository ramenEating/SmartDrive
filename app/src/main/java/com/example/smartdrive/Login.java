package com.example.smartdrive;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_ui);

        Button nonmember = (Button)findViewById(R.id.btn_nonmember);
        Button member = (Button)findViewById(R.id.btn_member);
        Button register = (Button)findViewById(R.id.btn_register);

        //로그인 버튼 클릭 이벤트
        member.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });
        //회원가입 버튼 클릭 이벤트
        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(Login.this, Register.class));
            }
        });
        //비회원 로그인 버튼 클릭 이벤트
        nonmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //로그인 -> 메인 화면으로 넘어갈 때 쓰일 인텐트 선언
                startActivity(new Intent(Login.this, MainActivity.class));
            }
        });
    }
}

