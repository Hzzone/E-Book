package com.example.hzzone.e_book;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.MenuItemHoverListener;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import okhttp3.*;


public class MainActivity extends AppCompatActivity {

//    private Button button;
//    private ImageView iv;
    private String URL = "http://www.biqukan.com/";
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Vector<Bookinfo> books = new Vector<>();
        Bookinfo book = null;
        new Thread(new Runnable() {
            @Override
            public void run() {
                book = Content.getBookinfo(URL, "一念永恒");
                Log.d(TAG, "run: ");
                
            }
        })
        books.add(Content.getBookinfo(URL, "一念永恒"));
        books.add(Content.getBookinfo(URL, "大泼猴"));
        books.add(Content.getBookinfo(URL, "奋斗在红楼"));
        books.add(Content.getBookinfo(URL, "文娱教父"));
        books.add(Content.getBookinfo(URL, "文娱教父"));
        books.add(Content.getBookinfo(URL, "文娱教父"));
        books.add(Content.getBookinfo(URL, "文娱教父"));
        books.add(Content.getBookinfo(URL, "文娱教父"));
        books.add(Content.getBookinfo(URL, "文娱教父"));
        books.add(Content.getBookinfo(URL, "文娱教父"));
        books.add(Content.getBookinfo(URL, "文娱教父"));
        books.add(Content.getBookinfo(URL, "文娱教父"));
        books.add(Content.getBookinfo(URL, "文娱教父"));
        books.add(Content.getBookinfo(URL, "文娱教父"));
        BookinfoAdapter adapter = new BookinfoAdapter(MainActivity.this,
                R.layout.bookinfo, books);
        ListView bookshelf_list = (ListView)findViewById(R.id.bookshelf_list);
        bookshelf_list.setAdapter(adapter);

//        button = (Button)findViewById(R.id.button);
//        iv = (ImageView) findViewById(R.id.iv);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d(TAG, "run: 已点击");
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
////                        Content.getChapterList("http://www.biqukan.com/", "http://www.biqukan.com/1_1094/");
////                        OkHttpClient client = new OkHttpClient();
////                        Request request = new Request.Builder().url("http://www.biqukan.com/1_1094/13404452.html").build();
////                        Response response = null;
////                        Content.getChapterContent("http://www.biqukan.com/",
////                                "http://www.biqukan.com/1_1094/13170873.html");
//                        //测试封面是否爬下来了
////                        final Bookinfo info = Content.getBookinfo("http://www.biqukan.com/", "一念永恒");
////                        runOnUiThread(new Runnable() {
////                            @Override
////                            public void run() {
////                                iv.setImageBitmap(info.getPic());
////                            }
////                        });
////                    }
////                }).start();
//                    }
//                });
//            }
//        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.edit_item:
                Log.d(TAG, "onOptionsItemSelected: clicked edit");
                break;
            case R.id.search_item:
                Log.d(TAG, "onOptionsItemSelected: clicked search");
                break;
            case R.id.setting_item:
                Log.d(TAG, "onOptionsItemSelected: clicked setting");
                break;
            default:
                Log.d(TAG, "onOptionsItemSelected: clicked nothing");
                break;
        }
        return true;
    }
}


