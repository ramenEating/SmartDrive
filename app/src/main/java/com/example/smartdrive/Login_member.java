package com.example.smartdrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login_member extends AppCompatActivity {
    EditText etId, etEmail;
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_member_ui);

        loginBtn = (Button)findViewById(R.id.loginBtn);
        etId = (EditText) findViewById(R.id.et_id); //아이디(닉네임) 입력
        etEmail = (EditText) findViewById(R.id.et_email); //이메일 입력

        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    String result = "";
                    String email = etEmail.getText().toString();
                    String id = etId.getText().toString();
                    String type = "login";

                    Login_registerActivity task = new Login_registerActivity();
                    result = task.execute(email, id, type).get();
                    String rr = result.substring(0, result.indexOf("!"));
                    //로그인 여부
                    if(rr.equals("loginO")) {
                        Toast.makeText(Login_member.this, "로그인 성공", Toast.LENGTH_LONG).show();
                        //로그인 성공이면 메인 페이지로 넘어가기
                        startActivity(new Intent(Login_member.this, MainActivity.class));
                    }
                    else if(rr.equals("loginXid")) Toast.makeText(Login_member.this, "ID가 일치하지 않습니다", Toast.LENGTH_LONG).show();
                    else if(rr.equals("loginXemail")) Toast.makeText(Login_member.this, "이메일이 일치하지 않습니다", Toast.LENGTH_LONG).show();
                    else if(rr.equals("loginX")) Toast.makeText(Login_member.this, "존재하지 않는 회원입니다", Toast.LENGTH_LONG).show();
                    else Toast.makeText(Login_member.this, "네트워크 연결 문제: 로그인할 수 없음", Toast.LENGTH_LONG).show();
                    //Log.i("WOW", rr);
                } catch (Exception e) {
                    //Log.i("DBtest", ".....ERROR.....!");
                }
            }
        });
    }
}
