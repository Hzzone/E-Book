package com.example.hzzone.e_book.JsonData;

/**
 * Created by Hzzone on 2017/5/6.
 */

public class Chapter {
    private String id;
    private String title;
    private String link;

    @Override
    public String toString() {
        return "Chapter{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", currency=" + currency +
                ", unreadble=" + unreadble +
                ", isVip=" + isVip +
                '}';
    }

    private int currency;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public boolean isUnreadble() {
        return unreadble;
    }

    public void setUnreadble(boolean unreadble) {
        this.unreadble = unreadble;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }

    private boolean unreadble;


    private boolean isVip;
}
