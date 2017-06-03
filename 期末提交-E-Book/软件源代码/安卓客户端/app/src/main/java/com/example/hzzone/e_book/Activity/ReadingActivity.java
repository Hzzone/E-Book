package com.example.hzzone.e_book.Activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.hzzone.e_book.Adapter.ChapterAdapter;
import com.example.hzzone.e_book.JsonData.Chapter;
import com.example.hzzone.e_book.JsonData.ChapterContent;
import com.example.hzzone.e_book.R;
import com.example.hzzone.e_book.Spider.Content;
import com.example.zhouwei.library.CustomPopWindow;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import java.io.Serializable;
import java.util.List;

import cn.xfangfang.paperviewlibrary.PaperLayout;
import cn.xfangfang.paperviewlibrary.PaperView;

public class ReadingActivity extends AppCompatActivity {
    private static final String TAG = "ReadingActivity";
    private PaperView paperView;
    //底部菜单栏
    private PopupWindow mPopupWindow;
    private View bottomView;
    //目录栏
    private CustomPopWindow mListPopWindow;
    //书籍章节列表
    private List<Chapter> chapters;

    //当前章节位置
    private int currentChapterIndex = 0;

    //获得章节地址
    private String getChapterResource(String chapter_ID){
        String chapter = "http://novel.juhe.im/chapters/http%3A%2F%2Fvip.zhuishushenqi.com%2Fchapter%2F" + chapter_ID + "%3Fcv%3D1486473051386l";
        Log.d(TAG, "getChapterResource: 章节内容的链接:\n"+chapter);
        return chapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        initPageView();
        paperView.setOnPaperViewStateListener(mStateListener);
        Intent intent = getIntent();
        String book_resource_url = intent.getStringExtra("book_resource_url");
        initChapterList(book_resource_url);
//        Log.d(TAG, "onCreate: "+"Chapter List size:"+chapters.size());
    }
    private void initChapterList(String book_resource_url){
        RxVolley.get(book_resource_url, new HttpCallback() {
            @Override
            public void onSuccess(String t) {

                Log.d(TAG, "onSuccess: 获取章节列表"+t);
                chapters = new Content(t).getBookChapterList();
                Log.d(TAG, "onSuccess: 获取第一章 "+chapters.get(0).toString());
                //设置第一章
                getChapterContent(0);
            }
        });
    }

    private void initPageView(){

        paperView = (PaperView)findViewById(R.id.paper_view);
        paperView.setContentPadding(16);
        //设置文字距离屏幕边框的距离

        paperView.setBackgroundColor(0xC8E6C9);
        paperView.setContentTextColor("#002505");
        paperView.setInfoTextColor("#8a000000");
        //设置背景、小说文字和其他信息的文字颜色
        paperView.setTextLine(17);
        paperView.setTextSize(20);
    }

    private void getChapterContent(int index){
        Chapter chapter = chapters.get(index);
        paperView.setChapterName(chapter.getTitle());
        //根据章节ID获取章节内容，并设置阅读界面
        RxVolley.get(getChapterResource(chapter.getId()), new HttpCallback() {
            @Override
            public void onSuccess(String t) {

                Log.d(TAG, "onSuccess: ChapterConten类信息:\n"+t);
                ChapterContent content = new Content(t).getChapterContent();
                Log.d(TAG, "onSuccess: "+"章节内容:\n"+content.getCpContent());
                paperView.setText(content.getCpContent());
            }
        });
    }
    //翻页监听器
    PaperLayout.StateListener mStateListener = new PaperLayout.StateListener() {
        @Override
        public void toStart() {
            Log.e(TAG, "toStart: " + "current index:"+currentChapterIndex);
            if(currentChapterIndex==0)
                Toast.makeText(getBaseContext(),"到头了",Toast.LENGTH_SHORT).show();
            else {
                getChapterContent(--currentChapterIndex);
            }

        }

        @Override
        public void toEnd() {
            Log.e(TAG, "toEnd: " + "current index:"+currentChapterIndex);
            if (currentChapterIndex==chapters.size()-1)
                Toast.makeText(getBaseContext(),"结束了",Toast.LENGTH_SHORT).show();
            else
                getChapterContent(currentChapterIndex++);
        }

        //点击屏幕中部出现底部菜单栏
        @Override
        public void centerClicked() {
            Log.e(TAG, "centerClicked: "+"点击了屏幕中央");
            if (bottomView==null)
                bottomView = LayoutInflater.from(ReadingActivity.this).inflate(R.layout.readingbottom, null);
            if (mPopupWindow==null) {
                mPopupWindow = new PopupWindow(bottomView,
                        LinearLayoutCompat.LayoutParams.MATCH_PARENT,
                        LinearLayoutCompat.LayoutParams.WRAP_CONTENT, true);
                mPopupWindow.setTouchable(true);
                mPopupWindow.setOutsideTouchable(true);
                mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
                mPopupWindow.showAtLocation(paperView, Gravity.BOTTOM, 0, 0);
            }
            mPopupWindow.showAtLocation(paperView, Gravity.BOTTOM, 0, 0);
            final RadioButton button1 = (RadioButton) bottomView.findViewById(R.id.button1);
            //设置夜间和白天模式的监听事件
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "onClick: "+"点击了模式按钮");
                    if (button1.getText().toString()=="夜间") {
                        paperView.setBackgroundColor(getResources().getColor(R.color.colorDark));
                        paperView.setContentTextColor("#E0D9E2");
                        button1.setText("白天");
                    }else {
                        button1.setText("夜间");
                        paperView.setBackgroundColor(getResources().getColor(R.color.colorDark));
                        paperView.setContentTextColor("#E0D9E2");
                        paperView.setBackgroundColor(0xC8E6C9);
                        paperView.setContentTextColor("#002505");
                    }
                }
            });

            //设置目录按钮的监听事件
            final RadioButton button5 = (RadioButton) bottomView.findViewById(R.id.button5);
            button5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e(TAG, "onClick: "+"点击了目录按钮");
                    Intent intent = new Intent(ReadingActivity.this, Chapterlist.class);
                    intent.putExtra("chapter_list", (Serializable)chapters);
                    startActivityForResult(intent, 1);
                    mPopupWindow.dismiss();
                    finish();
                }
            });
        }
    };

    //得到选择章节目录的返回章节号
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    int returnData = data.getIntExtra("choosen_chapter", 1);
                    Log.e(TAG, "onActivityResult: "+"章节活动返回的章节号:"+returnData);
                    currentChapterIndex = returnData;
                    getChapterContent(currentChapterIndex);
                }
                break;
            default:
        }
    }

}
