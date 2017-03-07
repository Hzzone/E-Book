package com.example.hzzone.e_book;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.Toast;

import com.wyt.searchbox.SearchFragment;
import com.wyt.searchbox.custom.IOnSearchClickListener;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import okhttp3.*;


public class MainActivity extends AppCompatActivity {

    private String URL = "http://www.biqukan.com/";
    private static final String TAG = "MainActivity";

    private SwipeRefreshLayout swipeRefreshLayout; //下拉刷新

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        swipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navigation =
                (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }


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
                SearchFragment searchFragment = SearchFragment.newInstance();
                searchFragment.setOnSearchClickListener(new IOnSearchClickListener() {
                    @Override
                    public void OnSearchClick(String keyword) {
                        //这里处理逻辑
                        Log.d(TAG, "OnSearchClick: " + keyword);
                    }
                });
                searchFragment.show(getSupportFragmentManager(), SearchFragment.TAG);
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
            Vector<Bookinfo> books = new Vector<>();
            Bookinfo book = Content.getBookinfo(URL, "一念永恒");
            books.add(book);
            books.add(new Bookinfo("1", "2", "1", book.getPic()));
            books.add(new Bookinfo("2", "2", "1", book.getPic()));
            books.add(new Bookinfo("3", "2", "1", book.getPic()));
            books.add(new Bookinfo("3", "2", "1", book.getPic()));
            books.add(new Bookinfo("4", "2", "1", book.getPic()));
            books.add(new Bookinfo("5", "2", "1", book.getPic()));
            books.add(new Bookinfo("6", "2", "1", book.getPic()));
            books.add(new Bookinfo("7", "2", "1", book.getPic()));
            books.add(new Bookinfo("8", "2", "1", book.getPic()));
            books.add(new Bookinfo("9", "2", "1", book.getPic()));
            publishProgress(books);
            if (books.isEmpty()) {
                return false;
            } else
                return true;
        }

        @Override
        protected void onProgressUpdate(Vector<Bookinfo>... values) {
            BookinfoAdapter adapter = new BookinfoAdapter(MainActivity.this,
                    R.layout.bookinfo, values[0]);
//            Log.d(TAG, "onCreate: "+books.get(0).getBookIntro());
            ListView bookshelf_list = (ListView) findViewById(R.id.bookshelf_list);
            bookshelf_list.setAdapter(adapter);

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
                    return true;
                case R.id.action_comment:
                    //TODO comment界面
                    return true;
                case R.id.action_notification:
                    //TODO Notification界面
                    return true;
                case R.id.action_settings:
                    //TODO Settings界面
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


