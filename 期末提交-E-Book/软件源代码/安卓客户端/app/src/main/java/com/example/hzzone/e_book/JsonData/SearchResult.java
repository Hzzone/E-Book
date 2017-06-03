package com.example.hzzone.e_book.JsonData;

/**
 * Created by Hzzone on 2017/5/6.
 */

public class SearchResult {

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public boolean isHasCp() {
        return hasCp;
    }

    public void setHasCp(boolean hasCp) {
        this.hasCp = hasCp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getShortIntro() {
        return shortIntro;
    }

    public void setShortIntro(String shortIntro) {
        this.shortIntro = shortIntro;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public double getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(double retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public int getBanned() {
        return banned;
    }

    public void setBanned(int banned) {
        this.banned = banned;
    }

    public int getLatelyFollower() {
        return latelyFollower;
    }

    public void setLatelyFollower(int latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "_id='" + _id + '\'' +
                ", hasCp=" + hasCp +
                ", title='" + title + '\'' +
                ", cat='" + cat + '\'' +
                ", author='" + author + '\'' +
                ", site='" + site + '\'' +
                ", cover='" + cover + '\'' +
                ", shortIntro='" + shortIntro + '\'' +
                ", lastChapter='" + lastChapter + '\'' +
                ", retentionRatio=" + retentionRatio +
                ", banned=" + banned +
                ", latelyFollower=" + latelyFollower +
                ", wordCount=" + wordCount +
                '}';
    }

    private String _id;
    private boolean hasCp;
    private String title;
    private String cat;
    private String author;
    private String site;
    private String cover;
    private String shortIntro;
    private String lastChapter;
    private double retentionRatio;
    private int banned;
    private int latelyFollower;
    private int wordCount;

}
