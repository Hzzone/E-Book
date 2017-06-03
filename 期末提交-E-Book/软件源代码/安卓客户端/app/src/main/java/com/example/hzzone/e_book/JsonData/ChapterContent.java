package com.example.hzzone.e_book.JsonData;

/**
 * Created by Hzzone on 2017/5/6.
 */

public class ChapterContent {
    @Override
    public String toString() {
        return "ChapterContent{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", isVip=" + isVip +
                ", cpContent='" + cpContent + '\'' +
                ", currency=" + currency +
                ", id='" + id + '\'' +
                '}';
    }

    private String title;
    private String body;
    private boolean isVip;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    public String getCpContent() {
        return cpContent;
    }

    public void setCpContent(String cpContent) {
        this.cpContent = cpContent;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String cpContent;
    private int currency;

    private String id;
}
