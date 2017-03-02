package com.example.hzzone.e_book;

import android.graphics.Bitmap;
import android.hardware.camera2.params.StreamConfigurationMap;

/**
 * Created by Hzzone on 2017/3/2.
 */

public class Bookinfo {
    private String bookName;
    private String bookIntro;
    private String bookURL;
    private Bitmap pic;
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

}
