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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity {
    private DatabaseReference mDatabase;
    EditText eName, eEmail;
    Button goregister;
    int userNum=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_ui);
        eName = (EditText) findViewById(R.id.et_name);
        eEmail = (EditText) findViewById(R.id.et_email);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        goregister = (Button) findViewById(R.id.btn_goregister);

        goregister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String getUserName = eName.getText().toString();
                String getUserEmail = eEmail.getText().toString();

                //hasmap 만들기
                HashMap result = new HashMap();
                result.put("name", getUserName);
                result.put("email", getUserEmail);

                for(int i=0; i<10; i++) userNum++;
                String un = String.valueOf(userNum);
                writeNewUser(un, getUserName, getUserEmail);
            }
        });
    }

    private void writeNewUser(String userId, String name, String email){
        User user = new User(name, email);
        mDatabase.child("users").child(userId).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(Register.this, "저장을 완료했습니다", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Register.this, "저장을 실패했습니다", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
