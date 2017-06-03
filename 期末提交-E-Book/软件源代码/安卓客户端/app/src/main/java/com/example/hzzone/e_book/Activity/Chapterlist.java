package com.example.hzzone.e_book.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hzzone.e_book.Adapter.ChapterAdapter;
import com.example.hzzone.e_book.JsonData.Chapter;
import com.example.hzzone.e_book.R;

import java.util.List;

public class Chapterlist extends AppCompatActivity {

    private ListView chapter_list;
    private static final String TAG = "Chapterlist";
    private List<Chapter> chapters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapterlist);
        chapter_list = (ListView) findViewById(R.id.chapter_list);
        chapters = (List<Chapter>) getIntent().getSerializableExtra("chapter_list");
        Log.e(TAG, "onCreate: 章节数量:"+chapters.size());
        chapter_list.setAdapter(new ChapterAdapter(this, R.layout.chapter, chapters));

        Log.e(TAG, "onCreate: " + "进入目录事件");
        chapter_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("choosen_chapter", position);
                setResult(RESULT_OK, intent);
            }
        });
    }
}
