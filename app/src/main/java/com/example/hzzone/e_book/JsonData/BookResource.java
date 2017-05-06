package com.example.hzzone.e_book.JsonData;

/**
 * Created by Hzzone on 2017/5/6.
 */

public class BookResource {
    private String _id;
    private String source;
    private String name;

    @Override
    public String toString() {
        return "BookResource{" +
                "_id='" + _id + '\'' +
                ", source='" + source + '\'' +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", lastChapter='" + lastChapter + '\'' +
                ", chaptersCount='" + chaptersCount + '\'' +
                ", updated='" + updated + '\'' +
                ", starting='" + starting + '\'' +
                ", host='" + host + '\'' +
                '}';
    }

    private String link;
    private String lastChapter;
    private String chaptersCount;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public String getChaptersCount() {
        return chaptersCount;
    }

    public void setChaptersCount(String chaptersCount) {
        this.chaptersCount = chaptersCount;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getStarting() {
        return starting;
    }

    public void setStarting(String starting) {
        this.starting = starting;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    private String updated;


    private String starting;
    private String host;
}
