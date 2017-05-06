package com.example.hzzone.e_book.Activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.hzzone.e_book.Adapter.BookAdapter;
import com.example.hzzone.e_book.Data.Book;
import com.example.hzzone.e_book.R;

import java.util.Vector;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";
    private BookAdapter bookAdapter;
    private SwipeMenuListView listView;
    private SwipeRefreshLayout swipeRefreshLayout; //下拉刷新
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        Vector<Book> books = new Vector<>();
        books.add(new Book("minorCate", "minorCate", "minorCate", "minorCate", "minorCate", "minorCate"));
        books.add(new Book("minorCate", "minorCate", "minorCate", "minorCate", "minorCate", "minorCate"));
        books.add(new Book("minorCate", "minorCate", "minorCate", "minorCate", "minorCate", "minorCate"));
        books.add(new Book("minorCate", "minorCate", "minorCate", "minorCate", "minorCate", "minorCate"));
        books.add(new Book("minorCate", "minorCate", "minorCate", "minorCate", "minorCate", "minorCate"));
        books.add(new Book("minorCate", "minorCate", "minorCate", "minorCate", "minorCate", "minorCate"));
        books.add(new Book("minorCate", "minorCate", "minorCate", "minorCate", "minorCate", "minorCate"));
        books.add(new Book("minorCate", "minorCate", "minorCate", "minorCate", "minorCate", "minorCate"));
        listView.setMenuCreator(creator);
        bookAdapter = new BookAdapter(MainActivity.this, R.layout.bookinfo, books);
        listView.setAdapter(bookAdapter);
        // 点击书单某一项的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        //点击侧滑菜单的事件
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // delete
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

}
