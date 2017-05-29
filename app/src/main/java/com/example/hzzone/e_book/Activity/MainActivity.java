package com.example.hzzone.e_book.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.hzzone.e_book.Adapter.BookAdapter;
import com.example.hzzone.e_book.Data.Book;
import com.example.hzzone.e_book.R;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";
    private BookAdapter bookAdapter;
    private SwipeMenuListView listView;
    private SwipeRefreshLayout swipeRefreshLayout; //下拉刷新
    List<Book> books = new ArrayList<>();
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建数据库
        SQLiteDatabase db = LitePal.getDatabase();
        //初始化数据库
        initDatabase();
        //从数据库中读取所有的书籍
        books = DataSupport.findAll(Book.class);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        searchView = (SearchView)findViewById(R.id.search_view);
        setSupportActionBar(toolbar);
        initView();
        initSwipe();
    }
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    private void initView(){
        listView = (SwipeMenuListView) findViewById(R.id.listView);
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(MainActivity.this, 90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };

// set creator
        listView.setMenuCreator(creator);
        bookAdapter = new BookAdapter(MainActivity.this, R.layout.bookinfo, books);
        listView.setAdapter(bookAdapter);
        // 点击书单某一项的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book = books.get(position);
                //启动阅读事件，并传递书源id
                Intent intent = new Intent(MainActivity.this, ReadingActivity.class);
                intent.putExtra("book_resource_url", book.getBook_resource_ID());
                Log.d(TAG, "onItemClick: "+book.getBook_resource_ID());
                startActivity(intent);
            }
        });
        //点击侧滑菜单的事件
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // delete
                        books.remove(position);
                        for(Book book:books)
                            book.save();
                        bookAdapter.notifyDataSetChanged();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

    }

    private void initSwipe(){
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark));
    }

    //下拉刷新监听
    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            // TODO 此处逻辑更新书籍
            Log.d(TAG, "onRefresh: "+"下拉刷新");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }
            }).start();
        }

    };


    private void initDatabase(){
        Book book = DataSupport.find(Book.class, 1);
        book.setBook_resource_ID("http://novel.juhe.im/book-chapters/56f8da09176d03ac1983f6cd");
        book.save();
//        new Book("50864bf69dacd30e3a000014", "http://novel.juhe.im/book-chapters/56f8da09176d03ac1983f6cd",
//                "http://image.cmfu.com/books/1735921/1735921.jpg",
//                "遮天", "辰东", "第一千八百二十二章 遮天大结局").save();
//        new Book("50864bf69dacd30e3a000014",
//                "56f8da09176d03ac1983f6cd",
//                "http://image.cmfu.com/books/1735921/1735921.jpg",
//                "遮天", "辰东", "第一千八百二十二章 遮天大结局").save();
//        new Book("50864bf69dacd30e3a000014",
//                "56f8da09176d03ac1983f6cd",
//                "http://image.cmfu.com/books/1735921/1735921.jpg",
//                "遮天", "辰东", "第一千八百二十二章 遮天大结局").save();
//        new Book("50864bf69dacd30e3a000014",
//                "56f8da09176d03ac1983f6cd",
//                "http://image.cmfu.com/books/1735921/1735921.jpg",
//                "遮天", "辰东", "第一千八百二十二章 遮天大结局").save();
//        new Book("50864bf69dacd30e3a000014",
//                "56f8da09176d03ac1983f6cd",
//                "http://image.cmfu.com/books/1735921/1735921.jpg",
//                "遮天", "辰东", "第一千八百二十二章 遮天大结局").save();
//        new Book("50864bf69dacd30e3a000014",
//                "56f8da09176d03ac1983f6cd",
//                "http://image.cmfu.com/books/1735921/1735921.jpg",
//                "遮天", "辰东", "第一千八百二十二章 遮天大结局").save();
//        new Book("50864bf69dacd30e3a000014",
//                "56f8da09176d03ac1983f6cd",
//                "http://image.cmfu.com/books/1735921/1735921.jpg",
//                "遮天", "辰东", "第一千八百二十二章 遮天大结局").save();
//        new Book("50864bf69dacd30e3a000014",
//                "56f8da09176d03ac1983f6cd",
//                "http://image.cmfu.com/books/1735921/1735921.jpg",
//                "遮天", "辰东", "第一千八百二十二章 遮天大结局").save();
    }
    //toolbar的检测事件
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem item = menu.findItem(R.id.action_search);
//        searchView.setMenuItem(item);

        return true;
    }
}
