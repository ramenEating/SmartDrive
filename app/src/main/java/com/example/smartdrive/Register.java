package com.example.smartdrive;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    private FirebaseFirestore mDatabase = FirebaseFirestore.getInstance();

    EditText eName, eEmail;
    Button goregister;
    int userNum = 0; //가입한 회원 숫자 세기(카운트)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_ui);

        eName = (EditText) findViewById(R.id.et_name); //이름 입력
        eEmail = (EditText) findViewById(R.id.et_email); //이메일 입력

        //회원가입 버튼
        goregister = (Button) findViewById(R.id.btn_goregister);
        goregister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //입력받은 글자 문자열로 저장
                String getUserName = eName.getText().toString();
                String getUserEmail = eEmail.getText().toString();

                //hasmap 만들기
                HashMap result = new HashMap();
                result.put("num", userNum);
                result.put("name", getUserName);
                result.put("email", getUserEmail);

                userNum++;
                //메소드 호출
                writeNewUser(userNum, getUserName, getUserEmail);
            }
        });
    }
    //만들어둔 User 객체 불러와서
    //db에 넣기
    private void writeNewUser(int num, String name, String email){
        User user = new User(num, name, email);
        mDatabase.collection("users").document("User").set(user);
    }
}
