package com.example.hzzone.e_book;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.jsoup.*;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

/**
 * Created by Hzzone on 2017/3/1.
 */


public class Content {

    private static final String TAG = "Content";

    /**
     * this function is to get all this book's chapter name and its url with different resource
     *
     * @param bookURL the target book's URL
     * @return the book chapter list and their URL
     */
    public static Map<String, String> getChapterList(String bookResource, String bookURL) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        switch (bookResource) {
            case "http://www.biqukan.com/":
                Elements list = doc.getElementsByClass("listmain");
                for (Element e1 : list) {
                    Elements chapters = e1.getElementsByTag("a");
                    for (Element e2 : chapters) {
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

    /**
     * @param bookResource   小说源网站
     * @param bookChapterURL 小说章节地址
     * @return 小说章节名和内容
     */
    public static Map<String, String> getChapterContent(String bookResource, String bookChapterURL) {
        Map<String, String> chapter = new HashMap<>();
        // 连接服务器
        Document doc = null;
        try {
            doc = Jsoup.connect(bookChapterURL).get();
        } catch (IOException e) {
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
//        Log.d(TAG, "getChapterContent: "+chapterName+"\n"+chapterContent);
//        Log.d(TAG, content.text());
        chapter.put(chapterName, chapterContent);
        return chapter;

    }

    /**
     * 爬取小说的介绍
     * @param bookResource 小说源网站
     * @param bookName 小说名
     * @return Bookinfo Object including one book's name, introduction, book cover, and book URL
     */
    public static Bookinfo getBookinfo(String bookResource, String bookName) {
        String picURL = null;
        String bookURL = null;
        String bookIntro = null;
        Bitmap pic = null;
        Document doc = null;
        String researchURL = "http://zhannei.baidu.com/cse/search?q="
                + bookName + "&click=1&s=2758772450457967865&nsid=";
        try {
            doc = Jsoup.connect(researchURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Log.d(TAG, "getBookinfo: "+doc.text());
        switch (bookResource) {
            case "http://www.biqukan.com/":
                Elements ele = doc.getElementsByClass("result-item result-game-item");
                Element e = ele.get(0); //只取搜索的第一本
                picURL = e.getElementsByTag("img").attr("src"); //封面的URL
                bookURL = ele.select("a.result-game-item-title-link").attr("href");

                bookIntro = ele.select("p.result-game-item-desc").get(0).text();
                break;
            default:
                Log.e(TAG, "源网站不存在");
                break;
        }
//        Log.d(TAG, "getBookinfo: "+bookName+"\n"+bookIntro+"\n"+bookURL+"\n"+picURL);
        // 下载小说封面
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(picURL).openConnection();
            conn.setRequestMethod("GET");//GET方式
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            //返回码
            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                //获得网络上返回的流
                InputStream is = conn.getInputStream();
                //将流转为图片
                pic = BitmapFactory.decodeStream(is);
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Log.d(TAG, "getBookinfo: "+pic);
        return new Bookinfo(bookName, bookIntro, bookURL, pic);
    }
}
