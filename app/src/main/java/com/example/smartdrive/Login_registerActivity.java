package com.example.smartdrive;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Login_registerActivity extends AsyncTask<String, Void, String> {
    String send, receive;

    @Override
    protected String doInBackground(String... strings) {
        try{
            String str = "";

            //접속할 서버 주소
            URL url = new URL("http://192.168.123.105:80/SD/androidDB.jsp");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter ows = new OutputStreamWriter(conn.getOutputStream());

            //jsp에 전송할 데이터(GET 방식으로 작성)
            send = "email=" + strings[0] + "&id=" + strings[1] + "&type=" + strings[2];

            ows.write(send);
            ows.flush();

            //jsp와 통신 성공 시 수행하는 코드
            if(conn.getResponseCode() == conn.HTTP_OK){
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "utf-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();

                //jsp에서 보낸 값 받기
                while((str = reader.readLine()) != null){
                    buffer.append(str);
                }
                receive = buffer.toString();
                //Log.i(this.getClass().getName(), receive);
            } else {
                //통신 실패 ㅜ
            }
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
        return receive;
    }
}
