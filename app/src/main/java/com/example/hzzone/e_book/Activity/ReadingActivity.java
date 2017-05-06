package com.example.hzzone.e_book.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hzzone.e_book.R;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import cn.xfangfang.paperviewlibrary.PaperView;

public class ReadingActivity extends AppCompatActivity {
    private static final String TAG = "ReadingActivity";
    private PaperView paperView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        paperView = (PaperView)findViewById(R.id.paper_view);
        paperView.setChapterName("a");
//        OuterMostData outerMostData = Content.parseObject()
                RxVolley.get("http://novel.juhe.im/search?keyword=%E9%81%AE%E5%A4%A9", new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {

//                        paperView.setText(new Content(t).getBookResource().toString());
                    }
                });
    }
}
