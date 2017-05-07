package com.example.hzzone.e_book.Activity;

import android.graphics.drawable.ColorDrawable;
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

import com.example.hzzone.e_book.JsonData.ChapterContent;
import com.example.hzzone.e_book.R;
import com.example.hzzone.e_book.Spider.Content;
import com.kymjs.rxvolley.RxVolley;
import com.kymjs.rxvolley.client.HttpCallback;

import cn.xfangfang.paperviewlibrary.PaperLayout;
import cn.xfangfang.paperviewlibrary.PaperView;

public class ReadingActivity extends AppCompatActivity {
    private static final String TAG = "ReadingActivity";
    private PaperView paperView;
    private PopupWindow mPopupWindow;
    private View bottomView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        initPageView();
        paperView.setOnPaperViewStateListener(mStateListener);
        paperView.setChapterName("a");
//        OuterMostData outerMostData = Content.parseObject()
                RxVolley.get("http://novel.juhe.im/chapters/http%3A%2F%2Fvip.zhuishushenqi.com%2Fchapter%2F56f8da09176d03ac1983f6d7%3Fcv%3D1486473051386", new HttpCallback() {
                    @Override
                    public void onSuccess(String t) {

                        ChapterContent chapterContent = new Content(t).getChapterContent();
                        paperView.setText(chapterContent.getCpContent());
                        paperView.setChapterName(chapterContent.getTitle());
                        Log.d(TAG, "onSuccess: "+chapterContent.getCpContent());
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
        paperView.setTextSize(17);
    }

    //翻页监听器
    PaperLayout.StateListener mStateListener = new PaperLayout.StateListener() {
        @Override
        public void toStart() {
            Toast.makeText(getBaseContext(),"到头了",Toast.LENGTH_SHORT).show();
        }

        @Override
        public void toEnd() {
            Toast.makeText(getBaseContext(),"结束了",Toast.LENGTH_SHORT).show();
        }

        //点击屏幕中部出现底部菜单栏
        @Override
        public void centerClicked() {
            Toast.makeText(getBaseContext(),"点击了中部",Toast.LENGTH_SHORT).show();
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
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
        }
    };
}
