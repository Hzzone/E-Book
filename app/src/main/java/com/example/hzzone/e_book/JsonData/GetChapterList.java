package com.example.hzzone.e_book.JsonData;

/**
 * Created by Hzzone on 2017/5/6.
 */

public class GetChapterList {
    private String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public String getChapters() {
        return chapters;
    }

    public void setChapters(String chapters) {
        this.chapters = chapters;
    }

    private String name;
    private String link;


    private String chapters;
    private String updated;

    @Override
    public String toString() {
        return "GetChapterList{" +
                "_id='" + _id + '\'' +
                ", name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", chapters='" + chapters + '\'' +
                ", updated='" + updated + '\'' +
                ", host='" + host + '\'' +
                '}';
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    private String host;
}
