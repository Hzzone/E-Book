package com.example.hzzone.e_book.Data;

import android.graphics.Bitmap;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

/**
 * Created by Hzzone on 2017/5/7.
 */

public class Book extends DataSupport{
    @Column(unique = true, nullable = false)
    private String book_ID;
    @Column(defaultValue = "567d3108092159dc71ad4ec8")
    private String book_resource_ID;

    public Book(String book_ID, String book_resource_ID, String cover_URL, String book_name, String author, String last_chapter) {
        this.book_ID = book_ID;
        this.book_resource_ID = book_resource_ID;
        this.cover_URL = cover_URL;
        this.book_name = book_name;
        this.author = author;
        this.last_chapter = last_chapter;
    }

    private String cover_URL;
    @Column(nullable = false)
    private String book_name;



    public String getBook_ID() {
        return book_ID;
    }

    public void setBook_ID(String book_ID) {
        this.book_ID = book_ID;
    }

    public String getBook_resource_ID() {
        return book_resource_ID;
    }

    public void setBook_resource_ID(String book_resource_ID) {
        this.book_resource_ID = book_resource_ID;
    }

    public String getCover_URL() {
        return cover_URL;
    }

    public void setCover_URL(String cover_URL) {
        this.cover_URL = cover_URL;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_ID='" + book_ID + '\'' +
                ", book_resource_ID='" + book_resource_ID + '\'' +
                ", cover_URL='" + cover_URL + '\'' +
                ", book_name='" + book_name + '\'' +
                ", author='" + author + '\'' +
                ", last_chapter='" + last_chapter + '\'' +
                '}';
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLast_chapter() {
        return last_chapter;
    }

    public void setLast_chapter(String last_chapter) {
        this.last_chapter = last_chapter;
    }

    private String author;
    private String last_chapter;
}
