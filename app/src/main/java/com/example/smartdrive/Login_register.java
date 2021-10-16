package com.example.smartdrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login_register extends AppCompatActivity {
    EditText etId, etEmail;
    Button registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_register_ui);

        registerBtn = (Button)findViewById(R.id.registerBtn);
        etId = (EditText) findViewById(R.id.et_id); //아이디(닉네임) 입력
        etEmail = (EditText) findViewById(R.id.et_email); //이메일 입력

        registerBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String result = "";
                    String email = etEmail.getText().toString();
                    String id = etId.getText().toString();
                    String type = "join";

                    //jsp와 통신 작업 클래스 생성 후 불러오기
                    Login_registerActivity task = new Login_registerActivity();
                    result = task.execute(email, id, type).get(); //통신 성공 후 받은 데이터
                    String rr = result.substring(0, result.indexOf("!"));
                    //가입 여부
                    if(rr.equals("joinO")) {
                        Toast.makeText(Login_register.this, "가입 성공", Toast.LENGTH_SHORT).show();
                        //가입 성공이면 로그인 페이지로 넘어가기
                        startActivity(new Intent(Login_register.this, Login_member.class));
                    }
                    else if(rr.equals("joinX")) Toast.makeText(Login_register.this, "이미 존재하는 이메일입니다", Toast.LENGTH_SHORT).show();
                    else Toast.makeText(Login_register.this, "네트워크 연결 문제: 가입할 수 없음", Toast.LENGTH_SHORT).show();
                    //Log.i("WOW", rr);
                } catch (Exception e) {
                    //Log.i("DBtest", ".....ERROR.....!");
                }
            }
        });
    }
}
