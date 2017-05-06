package com.example.hzzone.e_book.Spider;

import android.util.Log;

import com.example.hzzone.e_book.Data.BookChapter;
import com.example.hzzone.e_book.Data.Bookinfo;
import com.example.hzzone.e_book.Spider.Content;

/**
 * Created by Hzzone on 2017/5/6.
 */

public class TestContent {
    private static final String TAG = "TestContent";
    public static void main(String[] args){
        Bookinfo bookinfo = Content.getBookinfo("一念永恒");
    }
}
