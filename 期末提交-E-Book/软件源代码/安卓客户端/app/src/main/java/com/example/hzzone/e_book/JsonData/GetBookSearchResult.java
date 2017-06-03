package com.example.hzzone.e_book.JsonData;

/**
 * Created by Hzzone on 2017/5/6.
 */

public class GetBookSearchResult {

    public void setBooks(String books) {
        this.books = books;
    }

    public boolean isOk() {
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }

    public String getBooks() {
        return books;
    }

    private String books;

    private boolean ok;
}
