package com.example.hzzone.e_book;

import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.jsoup.*;
import android.util.Log;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
    public static Map<String, String> getChapterList(String bookResource, String bookURL){
        Map<String, String> chapterList = new LinkedHashMap<>();
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
                Elements list = doc.getElementsByClass("listmain");
                for(Element e1:list){
                    Elements chapters = e1.getElementsByTag("a");
                    for(Element e2:chapters){
                        String chapterName = e2.text();
                        String chapterURL = e2.attr("href");
                        // TODO 这里章节目录需要解析才能恰当的显示，主要问题是网站前面有一段最近更新，以及章节有数字有中文
//                        Log.d(TAG, chapterName + " " + chapterURL);
                        chapterList.put(chapterName, chapterURL);
                    }
                }
                break;
            default:
                Log.e(TAG, "getChapterList: 目标书的源网站不存在");
                break;
        }
//        // 测试章节目录是否正确
//        Set set = chapterList.keySet();
//        for(Iterator i = set.iterator();i.hasNext();){
//            String key = (String)i.next();
//            String value = (String)chapterList.get(key);
//            Log.d(TAG, key + " " + value);
//        }
        return chapterList;
    }

    public static Map<String, String> getChapterContent(String bookResource, String bookChapterURL){
        Map<String, String> chapter = new HashMap<>();
        // 连接服务器
        Document doc = null;
        try {
            doc = Jsoup.connect(bookChapterURL).get();
        }catch (IOException e){
            e.printStackTrace();
        }
        Elements content = null;
        String chapterName = null; //章节名
        String chapterContent = null; //章节内容
        switch (bookResource) {
            case "http://www.biqukan.com/":
                content = doc.select("div.content");
                Elements name = doc.getElementsByTag("h1");
                Element con = doc.getElementById("content");
                chapterName = name.html();
                chapterContent = con.html();
//                Log.d(TAG, "getChapterContent: "+chapterName+"\n"+chapterContent);
//                Log.d(TAG, "getChapterContent: "+name.text()+"\n"+con.html());
                break;
            default:
                Log.e(TAG, "书源不存在");
                break;
        }
        chapterContent = chapterContent.replaceAll("(?i)<br[^>]*>", "br2n");
        chapterContent = chapterContent.replaceAll("br2n", "\n");
        Log.d(TAG, "getChapterContent: "+chapterName+"\n"+chapterContent);
//        Log.d(TAG, content.text());
        return chapter;
    }
}
