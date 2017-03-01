package com.example.hzzone.e_book;

import java.util.Map;
import java.util.Vector;

import org.jsoup.*;
import android.util.Log;

import java.io.IOException;

import org.jsoup.nodes.Document;
/**
 * Created by Hzzone on 2017/3/1.
 */


public class Content {

    private static final String TAG = "Content";

    /**
     * this function is to get all this book's chapter name and its url with different resource
      * @param bookURL the target book's URL
     * @return the book chapter list and their URL
     */
    public static Vector<Map<String, String>> getChapterList(String bookResource, String bookURL){
        Vector<Map<String, String>> chapterList = new Vector<>();
//        try {
////                            response = client.newCall(request).execute();
////                            Log.d(TAG, response.body().string());
//            Document doc = Jsoup.connect("http://zhannei.baidu.com/cse/search?q=一念永恒&click=1&s=2758772450457967865&nsid=").get();
////                            Element
////                            Log.d(TAG, s);
//        }catch (IOException e){
//            e.printStackTrace();
//            Log.d(TAG, "服务器连接失败");
//        }
        // 连接服务器
        Document doc = null;
        try {
             doc = Jsoup.connect(bookURL).get();
        }catch (IOException e){
            e.printStackTrace();
        }
        switch (bookResource){
            case "http://www.biqukan.com/":
                
                break;
            default:
                Log.e(TAG, "getChapterList: 目标书的源网站不存在");
                break;
        }
        return null;
    }
}
