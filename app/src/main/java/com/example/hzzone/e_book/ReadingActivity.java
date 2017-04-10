package com.example.hzzone.e_book;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

public class ReadingActivity extends AppCompatActivity {

    TextView reading_text;
    private static final String TAG = "ReadingActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading);
        reading_text = (TextView) findViewById(R.id.reading_text);
        Intent intent = getIntent();
        String URL = intent.getStringExtra("book_url");
        new ReadingTask().execute();
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
            reading_text.setText(values[0]);
            reading_text.setMovementMethod(ScrollingMovementMethod.getInstance());
        }

    }
    private static String deleteCRLFOnce(String input) {

        input.replace("&nbsp;", "");
        return input.replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1");

    }
}
