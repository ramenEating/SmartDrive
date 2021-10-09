package com.example.smartdrive;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.net.URL;

public class SearchBar extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //맨 처음 보일 gridview
        changeView(0) ;

        Button button1 = (Button) findViewById(R.id.search_record);
        button1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView(0);
            }
        });

        Button button2 = (Button) findViewById(R.id.search_favorite);
        button2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView(1);
            }
        });

        Button button3 = (Button) findViewById(R.id.search_local);
        button3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeView(2);
            }
        });
    }


    private void changeView(int index) {

        // 자식(Children) 뷰들에 대한 참조 획득.
        TextView textView1 = (TextView) findViewById(R.id.text1) ;
        TextView textView2 = (TextView) findViewById(R.id.text2) ;
        TextView textView3 = (TextView) findViewById(R.id.text3) ;

        // index에 따라 자식(Children) 뷰 들의 visibility 설정.
        switch (index) {
            case 0 :
                textView1.setVisibility(View.VISIBLE) ;
                textView2.setVisibility(View.INVISIBLE) ;
                textView3.setVisibility(View.INVISIBLE) ;
                break ;
            case 1 :
                textView1.setVisibility(View.INVISIBLE) ;
                textView2.setVisibility(View.VISIBLE) ;
                textView3.setVisibility(View.INVISIBLE) ;
                break ;
            case 2 :
                textView1.setVisibility(View.INVISIBLE) ;
                textView2.setVisibility(View.INVISIBLE) ;
                textView3.setVisibility(View.VISIBLE) ;
                break ;
        }
    }

}