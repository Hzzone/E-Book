package com.example.hzzone.e_book.Spider;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.jsoup.*;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.hzzone.e_book.Data.BookChapter;
import com.example.hzzone.e_book.Data.Bookinfo;
import com.example.hzzone.e_book.Data.ChapterList;

import java.io.IOException;
import java.util.Vector;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by Hzzone on 2017/3/1.
 */


public class Content {

    private static final String TAG = "Content";
    private static final String bookResource = "http://www.biqukan.com/";
    private static Document doc;

    /**
     * this function is to get all this book's chapter name and its url with different resource
     *
     * @param bookURL the target book's URL
     * @return the book chapter list and their URL
     */
    public static ChapterList getChapterList(String bookURL) {
        String bookResource = "http://www.biqukan.com/";
        Vector<BookChapter> bookChapters = null;
        // 连接服务器
        try {
            doc = Jsoup.connect(bookURL).get();
        }catch (IOException e) {
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
                        bookChapters.add(new BookChapter(chapterName, chapterURL));
                    }
                }
                break;
            default:
                Log.e(TAG, "getChapterList: 目标书的源网站不存在");
                break;
        }
        return new ChapterList(bookChapters);
    }

    /**
     * @param bookChapterURL 小说章节地址
     * @return 小说章节名和内容
     */
    public static BookChapter getChapterContent(final String bookChapterURL) {
        String bookResource = "http://www.biqukan.com/";
        // 连接服务器
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
                break;
            default:
                Log.e(TAG, "书源不存在");
                break;
        }
        return new BookChapter(chapterName, chapterContent);

    }

    /**
     * 爬取小说的介绍
     * @param bookName 小说名
     * @return Bookinfo Object including one book's name, introduction, book cover, and book URL
     */
    public static Bookinfo getBookinfo(String bookName) {
        String bookResource = "http://www.biqukan.com/";
        String bookURL = null;
        String bookIntro = null;
        Bitmap pic = null;
        String picURL = null;
        String researchURL = "http://zhannei.baidu.com/cse/search?q="
                + bookName + "&click=1&s=2758772450457967865&nsid=";
        try {
            doc = Jsoup.connect(researchURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        // 下载小说封面
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) new URL(picURL).openConnection();
            conn.setRequestMethod("GET");//GET方式
            conn.setConnectTimeout(10000);
            conn.setReadTimeout(10000);
            //返回码
            if (conn.getResponseCode() == 200) {
                //获得网络上返回的流
                InputStream is = conn.getInputStream();
                //将流转为图片
                pic = BitmapFactory.decodeStream(is);
                System.out.print("get");
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Bookinfo(bookName, bookIntro, bookURL, pic);
    }
}
