package com.example.hzzone.e_book;

import android.os.AsyncTask;
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
        new UpadteBooksTask().execute();

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

    class UpadteBooksTask extends AsyncTask<Void, Vector<Bookinfo>, Boolean> {

        @Override
        protected Boolean doInBackground(Void... params) {
//            Bookinfo book = Content.getBookinfo(URL, "一念永恒");
//            Log.d(TAG, "doInBackground: "+book.getBookIntro());
            Vector<Bookinfo> books = new Vector<>();
            books.add(Content.getBookinfo(URL, "一念永恒"));
            publishProgress(books);
            if(books.isEmpty()){
                return false;
            }else
                return true;
        }

        @Override
        protected void onProgressUpdate(Vector<Bookinfo>... values){
            BookinfoAdapter adapter = new BookinfoAdapter(MainActivity.this,
                    R.layout.bookinfo, values[0]);
//            Log.d(TAG, "onCreate: "+books.get(0).getBookIntro());
            ListView bookshelf_list = (ListView)findViewById(R.id.bookshelf_list);
            bookshelf_list.setAdapter(adapter);

        }

        @Override
        protected void onPostExecute(Boolean result){
            if(result){
                Toast.makeText(getApplicationContext(), "update success", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getApplicationContext(), "update failed", Toast.LENGTH_SHORT).show();
            }
        }
    }
}


