package com.example.hzzone.e_book;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;



public class MainActivity extends AppCompatActivity {

    private String URL = "http://www.biqukan.com/";
    private static final String TAG = "MainActivity";

    private SwipeRefreshLayout swipeRefreshLayout; //下拉刷新
    private ViewPager mViewPager = null;
    private BottomNavigationView navigation;
    //书架
    private ListView bookshelf_list;
    private ArrayList<View> mListViews = new ArrayList<>();
    Vector<Bookinfo> books = new Vector<>();
    BookinfoAdapter adapter;
    //底部菜单栏
    private View home;
    private View comment;
    private View notification;
    private View settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        initViewPager();
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigation =
                (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mViewPager.addOnPageChangeListener(mPageChangeListener);
    }

    void initViewPager(){
        LayoutInflater mLayoutInflater = LayoutInflater.from(this);
        home = mLayoutInflater.inflate(R.layout.home, null);
        comment = mLayoutInflater.inflate(R.layout.comment, null);
        notification = mLayoutInflater.inflate(R.layout.notification, null);
        settings = mLayoutInflater.inflate(R.layout.settings, null);
        swipeRefreshLayout = (SwipeRefreshLayout) home.findViewById(R.id.swipe_refresh);
        bookshelf_list = (ListView) home.findViewById(R.id.bookshelf_list);

        books.clear();
        // 测试书架
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", BitmapFactory.decodeFile("test.jpg")));
        adapter = new BookinfoAdapter(MainActivity.this,
                R.layout.bookinfo, books);
        bookshelf_list.setAdapter(adapter);
        bookshelf_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, books.get(position).getBookName(), Toast.LENGTH_LONG).show();
                Bookinfo book = books.get(position);
                Intent intent = new Intent(MainActivity.this, ReadingActivity.class);
                intent.putExtra("book_intro", book.getBookIntro());
                intent.putExtra("book_name", book.getBookName());
                intent.putExtra("book_url", book.getBookURL());
                startActivity(intent);
            }
        });

        mListViews.add(home);
        mListViews.add(comment);
        mListViews.add(notification);
        mListViews.add(settings);
        mViewPager.setAdapter(new MyViewPagerAdapter(mListViews));
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            View view;
            switch (position){
                case 0:
//                    navigation.setItemTextColor(findViewById(R.color.));
                    Log.d(TAG, "onPageSelected: 0");
                    view = navigation.findViewById(R.id.action_home);
                    view.performClick();
                    break;
                case 1:
                    Log.d(TAG, "onPageSelected: 1");
                    view = navigation.findViewById(R.id.action_comment);
                    view.performClick();
                    break;
                case 2:
                    Log.d(TAG, "onPageSelected: 2");
                    view = navigation.findViewById(R.id.action_notification);
                    view.performClick();
                    break;
                case 3:
                    Log.d(TAG, "onPageSelected: 3");
                    view = navigation.findViewById(R.id.action_settings);
                    view.performClick();
                    break;
                default:
                    Log.d(TAG, "onPageSelected: error!");
                    break;
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit_item:
                Log.d(TAG, "onOptionsItemSelected: clicked edit");
                break;
            case R.id.search_item:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;
            default:
                Log.d(TAG, "onOptionsItemSelected: clicked nothing");
                break;
        }
        return true;
    }

    /**
     * 更新书架列表以及小说章节
     */
    class UpadteBooksTask extends AsyncTask<Void, Vector<Bookinfo>, Boolean> {

        //TODO 书架的数据库存取和小说更新的爬取
        @Override
        protected Boolean doInBackground(Void... params) {
            Bookinfo book = Content.getBookinfo(URL, "一念永恒");
            books.add(book);
            books.add(new Bookinfo("1", "2", "http://www.biqukan.com/1_1094/", book.getPic()));
            books.add(new Bookinfo("2", "2", "http://www.biqukan.com/1_1094/", book.getPic()));
            books.add(new Bookinfo("3", "2", "http://www.biqukan.com/1_1094/", book.getPic()));
            books.add(new Bookinfo("3", "2", "http://www.biqukan.com/1_1094/", book.getPic()));
            books.add(new Bookinfo("4", "2", "http://www.biqukan.com/1_1094/", book.getPic()));
            books.add(new Bookinfo("5", "2", "http://www.biqukan.com/1_1094/", book.getPic()));
            books.add(new Bookinfo("6", "2", "http://www.biqukan.com/1_1094/", book.getPic()));
            books.add(new Bookinfo("7", "2", "http://www.biqukan.com/1_1094/", book.getPic()));
            books.add(new Bookinfo("8", "2", "http://www.biqukan.com/1_1094/", book.getPic()));
            books.add(new Bookinfo("9", "2", "http://www.biqukan.com/1_1094/", book.getPic()));
            publishProgress(books);
            if (books.isEmpty()) {
                return false;
            } else
                return true;
        }

        @Override
        protected void onProgressUpdate(Vector<Bookinfo>... values) {
            adapter.addAll(values[0]);

        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                Toast.makeText(getApplicationContext(), "update success", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "update failed", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * 底部菜单点击监听
     */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_home:
                    //TODO 主界面显示书架和更新
                    mViewPager.setCurrentItem(0);
                    return true;
                case R.id.action_comment:
                    //TODO comment界面
                    mViewPager.setCurrentItem(1);
                    return true;
                case R.id.action_notification:
                    //TODO Notification界面
                    mViewPager.setCurrentItem(2);
                    return true;
                case R.id.action_settings:
                    //TODO Settings界面
                    mViewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }

    };

    private SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            // TODO 此处逻辑更新书籍
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        new UpadteBooksTask().execute();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "run: 刷新");
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


