package com.example.smartdrive;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {
    public int num;
    public String userName;
    public String email;

    public User() { }
    public User(int num, String userName, String email){
        this.num = num;
        this.userName = userName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num){
        this.num = num;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{ num="+num+"userName="+userName+", email="+email+"}";
    }
}

