package com.example.hzzone.e_book.Data;

import java.util.Vector;

/**
 * Created by Hzzone on 2017/5/6.
 */

public class ChapterList {
    private Vector<BookChapter> chapters;

    public Vector<BookChapter> getChapters() {
        return chapters;
    }

    public void setChapters(Vector<BookChapter> chapters) {
        this.chapters = chapters;
    }

    public ChapterList(Vector<BookChapter> chapters) {

        this.chapters = chapters;
    }
}
