package com.example.hzzone.e_book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import okhttp3.*;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "run: 已点击");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Content.getChapterList("http://www.biqukan.com/", "http://www.biqukan.com/1_1094/");
//                        OkHttpClient client = new OkHttpClient();
//                        Request request = new Request.Builder().url("http://www.biqukan.com/1_1094/13404452.html").build();
//                        Response response = null;
                    }
                }).start();
            }
        });
    }
}
