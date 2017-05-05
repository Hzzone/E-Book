package com.example.hzzone.e_book;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;

import com.example.hzzone.e_book.Spider.Content;


public class ReadingActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "ReadingActivity";
    //用来更新菜单栏
//    @Bind(R.id.button1)
//    Button button1;
//    @Bind(R.id.button2)
//    Button button2;
//    @Bind(R.id.button3)
//    Button button3;
//    @Bind(R.id.button4)
//    Button button4;
    View bottomView;
    RadioButton button1;
    RadioButton button2;
    RadioButton button3;
    RadioButton button4;
    private PopupWindow mBottomPopWindow;
//    private PopupWindow mTopPopWindow;
    PageWidget pageWidget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        pageWidget = new PageWidget(this);
        LinearLayout reading_layout = (LinearLayout)findViewById(R.id.activity_reading);
        reading_layout.addView(pageWidget, LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                LinearLayoutCompat.LayoutParams.MATCH_PARENT);
//        button2.setOnClickListener(this);
//        button3.setOnClickListener(this);
//        button4.setOnClickListener(this);
        Intent intent = getIntent();
        String URL = intent.getStringExtra("book_url");
//        new ReadingTask().execute();
    }

    //活动的点击事件
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button1:
                Log.d(TAG, "onClick: " + "button1");
        }

    }

    // 读书
    class ReadingTask extends AsyncTask<Void, String, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String text =
                    Content.getChapterContent("http://www.biqukan.com/", "http://www.biqukan.com/1_1094/13662709.html").values().toString();
            deleteCRLFOnce(text);
            Log.d(TAG, "doInBackground: "+text);
            publishProgress(text);
            return null;
        }
        protected void onProgressUpdate(String... values){
            //TODO 更新页面
        }

    }
    private static String deleteCRLFOnce(String input) {

        input.replace("&nbsp;", "");
        return input.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1");

    }

    class PageWidget extends View{

        private static final String TAG = "PageWidget";
        private int mScreenWidth = 0; // 屏幕宽
        private int mScreenHeight = 0; // 屏幕高



        public PageWidget(Context context) {
            super(context);
            WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metric = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(metric);
            mScreenWidth = metric.widthPixels;
            mScreenHeight = metric.heightPixels;
            Log.d(TAG, "PageWidget: " + mScreenHeight + " " + mScreenWidth);
        }

        @Override
        protected void onDraw(Canvas canvas){
            super.onDraw(canvas);
        }


        /**
         * 监控屏幕点击事件
         */
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
            int x = (int)event.getX();
            int y = (int)event.getY();
            //防止onTouchEvent点下和离开
            if (event.getAction() == MotionEvent.ACTION_UP) {
                Log.d(TAG, "onTouchEvent: " + x + " " + y);
                if (x > mScreenWidth / 5 && x < mScreenWidth * 4 / 5 && y > mScreenHeight / 3 && y < mScreenHeight * 2 / 3) {
                    //TODO 点击中央时出现顶部与底部菜单栏
//                    View topView = LayoutInflater.from(ReadingActivity.this).inflate(R.layout.readingtop, null);
                    bottomView = LayoutInflater.from(ReadingActivity.this).inflate(R.layout.readingbottom, null);
//                    final View topView = LayoutInflater.from(ReadingActivity.this).inflate(R.layout.readingtop, null);

                    mBottomPopWindow = new PopupWindow(bottomView,
                            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                            LinearLayoutCompat.LayoutParams.WRAP_CONTENT, true);
//                    mTopPopWindow = new PopupWindow(topView,
//                            LinearLayoutCompat.LayoutParams.MATCH_PARENT,
//                            LinearLayoutCompat.LayoutParams.WRAP_CONTENT, true);
//                    mTopPopWindow.setTouchable(true);
//                    mTopPopWindow.setOutsideTouchable(true);
//                    mTopPopWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
//                    mTopPopWindow.showAtLocation(pageWidget, Gravity.TOP, 0, 0);
                    mBottomPopWindow.setTouchable(true);
                    mBottomPopWindow.setOutsideTouchable(true);
                    mBottomPopWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
                    mBottomPopWindow.showAtLocation(pageWidget, Gravity.BOTTOM, 0, 0);
                    button1 = (RadioButton) bottomView.findViewById(R.id.button1);
                    button1.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "onClick: "+"button1");
                        }
                    });
                    button2 = (RadioButton) bottomView.findViewById(R.id.button1);
                    button2.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "onClick: "+"button2");
                        }
                    });
                    button3 = (RadioButton) bottomView.findViewById(R.id.button1);
                    button3.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "onClick: "+"button3");
                        }
                    });
                    button4 = (RadioButton) bottomView.findViewById(R.id.button1);
                    button4.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.d(TAG, "onClick: "+"button3");
                        }
                    });
                    // 顶部消失时底部同时消失
//                    mTopPopWindow.setOnDismissListener(new android.widget.PopupWindow.OnDismissListener(){
//                        public void onDismiss() {
//                            mBottomPopWindow.dismiss();
//                        }
//
//                    });
//                    mBottomPopWindow.setOnDismissListener(new android.widget.PopupWindow.OnDismissListener(){
//                        public void onDismiss() {
//                            mTopPopWindow.dismiss();
//                        }
//
//                    });
                    Log.d(TAG, "onTouchEvent: " + "Clicked center");
                } else if (x < mScreenWidth / 2) {
                    //TODO 点击了屏幕左边
                    Log.d(TAG, "onTouchEvent: " + "Clicked left");
                } else {
                    //TODO 点击了屏幕右边
                    Log.d(TAG, "onTouchEvent: " + "Clicked right");
                }
            }
            return true;
        }
    }
}
