package com.example.hzzone.e_book;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.hzzone.e_book.Data.BookChapter;
import com.example.hzzone.e_book.Data.ChapterList;
import com.example.hzzone.e_book.Spider.Content;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;
import com.kymjs.rxvolley.toolbox.Loger;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.xfangfang.paperviewlibrary.PaperLayout;
import cn.xfangfang.paperviewlibrary.PaperView;


public class ReadingActivity extends AppCompatActivity{

    private static final String TAG = "ReadingActivity";
    private View bottomView;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private RadioButton button4;
    private PopupWindow mBottomPopWindow;
    //章节目录遍历
    private int bookChapterIterator = 0;
    private PaperView paperView;
    private ChapterList chapterList;
    //书的链接
    String URL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        Intent intent = getIntent();
        URL = intent.getStringExtra("book_url");
        new ReadingTask().execute();
        Log.d(TAG, "onCreate: " + URL);
        initView();
    }

    private void initView(){
        paperView = (PaperView) findViewById(R.id.paper_view);
        paperView.setTextLine(17);
        paperView.setTextSize(17);
        paperView.setContentTextColor("#002505");
        paperView.setInfoTextColor("#8a000000");

//        paperView.setOnPaperViewStateListener(new PaperLayout.StateListener() {
//            @Override
//            public void toStart() {
//                if (bookChapterIterator == 0) {
//                    Toast.makeText(getBaseContext(), "到头了", Toast.LENGTH_SHORT).show();
//                }else {
//                    bookChapterIterator--;
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            ChapterList chapterList = Content.getChapterList(URL);
//                            //获得前一个章节的内容
//                            final BookChapter chapter = chapterList.getChapters().elementAt(bookChapterIterator);
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    paperView.setText(chapter.getChapter());
//                                    paperView.setChapterName(chapter.getChapterName());
//                                }
//                            });
//                        }
//                    }).start();
//                }
//            }
//
//            @Override
//            public void toEnd() {
//                if (bookChapterIterator==chapterList.getChapters().size()-1) {
//                    Toast.makeText(getBaseContext(), "结束了", Toast.LENGTH_SHORT).show();
//                }else {
//                    bookChapterIterator++;
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            ChapterList chapterList = Content.getChapterList(URL);
//                            //获得前一个章节的内容
//                            final BookChapter chapter = chapterList.getChapters().elementAt(bookChapterIterator);
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    paperView.setText(chapter.getChapter());
//                                    paperView.setChapterName(chapter.getChapterName());
//                                }
//                            });
//                        }
//                    }).start();
//                }
//            }

//            @Override
//            public void centerClicked() {
//                showMenu();
//                Toast.makeText(getBaseContext(),"点击了中部",Toast.LENGTH_SHORT).show();
//            }
//        });
        RxVolley.get("http://novel.juhe.im/chapters/http%3A%2F%2Fvip.zhuishushenqi.com%2Fchapter%2F56f8da09176d03ac1983f6d7%3Fcv%3D1486473051386", new HttpCallback() {
            @Override
            public void onSuccess(String t) {
                paperView.setText(t);
            }
        });
    }

    // 获得章节目录
    class ReadingTask extends AsyncTask<Void, String, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            Log.d(TAG, "doInBackground: " + "获得章节目录");
//            chapterList = Content.getChapterList(URL);
            return null;
        }

    }

    private void showMenu(){
        bottomView = LayoutInflater.from(ReadingActivity.this).inflate(R.layout.readingbottom, null);

        mBottomPopWindow = new PopupWindow(bottomView,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.WRAP_CONTENT, true);
        mBottomPopWindow.setTouchable(true);
        mBottomPopWindow.setOutsideTouchable(true);
        mBottomPopWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        mBottomPopWindow.showAtLocation(paperView, Gravity.BOTTOM, 0, 0);
        button1 = (RadioButton) bottomView.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+"button1");
            }
        });
        button2 = (RadioButton) bottomView.findViewById(R.id.button1);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+"button2");
            }
        });
        button3 = (RadioButton) bottomView.findViewById(R.id.button1);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+"button3");
            }
        });
        button4 = (RadioButton) bottomView.findViewById(R.id.button1);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: "+"button3");
            }
        });
    }
}
