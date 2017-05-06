package com.example.hzzone.e_book.Data;

/**
 * Created by Hzzone on 2017/5/6.
 */

public class BookChapter {
    public String getChapterName() {
        return chapterName;
    }




    private String chapterName;


    public BookChapter(String chapterName, String chapter) {
        this.chapterName = chapterName;
        this.chapter = chapter;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    private String chapter;
}
