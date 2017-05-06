package com.example.hzzone.e_book.Data;

import android.graphics.Bitmap;
import android.graphics.drawable.VectorDrawable;
import android.hardware.camera2.params.StreamConfigurationMap;

import java.util.Map;

/**
 * Created by Hzzone on 2017/3/2.
 */

public class Bookinfo {
    private String bookName;
    private String bookIntro;
    private String bookURL;
    private Bitmap pic;
    private Map<String, String> Chapter;
    public Bookinfo(String bookName, String bookIntro,String bookURL, Bitmap pic){
        this.bookName = bookName;
        this.bookIntro = bookIntro;
        this.bookURL = bookURL;
        this.pic = pic;
    }
    public void setBookName(String bookName){
        this.bookName = bookName;
    }
    public void setBookIntro(String bookIntro){
        this.bookIntro = bookIntro;
    }
    public void setBookURL(String bookURL){
        this.bookURL = bookURL;
    }
    public void setPic(Bitmap pic){
        this.pic = pic;
    }
    public String getBookName(){
        return bookName;
    }
    public String getBookIntro(){
        return bookIntro;
    }
    public String getBookURL(){
        return bookURL;
    }
    public Bitmap getPic(){
        return pic;
    }

    public Map<String, String> getChapter() {
        return Chapter;
    }

    public void setChapter(Map<String, String> chapter) {
        Chapter = chapter;
    }
}
