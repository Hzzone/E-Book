package com.example.hzzone.e_book.JsonData;

/**
 * Created by Hzzone on 2017/5/7.
 */

public class BookDetails {
    private String _id;
    private String author;
    private String cover;
    private String creater;
    private String longIntro;
    private String title;
    private String cat;
    private String majorCate;
    private String minorCate;
    private int currency;
    private String contentType;
    private boolean _le;
    private boolean allowMonthly;
    private boolean allowVoucher;
    private boolean allowBeanVoucher;
    private boolean hasCp;
    private int postCount;
    private int latelyFollower;
    private int followerCount;
    private int wordCount;
    private int serializeWordCount;
    private String retentionRatio;
    private String updated;
    private boolean isSerial;
    private int chaptersCount;
    private String lastChapter;
    private String gender;
    private String tags;

    @Override
    public String toString() {
        return "BookDetails{" +
                "_id='" + _id + '\'' +
                ", author='" + author + '\'' +
                ", cover='" + cover + '\'' +
                ", creater='" + creater + '\'' +
                ", longIntro='" + longIntro + '\'' +
                ", title='" + title + '\'' +
                ", cat='" + cat + '\'' +
                ", majorCate='" + majorCate + '\'' +
                ", minorCate='" + minorCate + '\'' +
                ", currency=" + currency +
                ", contentType='" + contentType + '\'' +
                ", _le=" + _le +
                ", allowMonthly=" + allowMonthly +
                ", allowVoucher=" + allowVoucher +
                ", allowBeanVoucher=" + allowBeanVoucher +
                ", hasCp=" + hasCp +
                ", postCount=" + postCount +
                ", latelyFollower=" + latelyFollower +
                ", followerCount=" + followerCount +
                ", wordCount=" + wordCount +
                ", serializeWordCount=" + serializeWordCount +
                ", retentionRatio='" + retentionRatio + '\'' +
                ", updated='" + updated + '\'' +
                ", isSerial=" + isSerial +
                ", chaptersCount=" + chaptersCount +
                ", lastChapter='" + lastChapter + '\'' +
                ", gender='" + gender + '\'' +
                ", tags='" + tags + '\'' +
                ", donate=" + donate +
                '}';
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getLongIntro() {
        return longIntro;
    }

    public void setLongIntro(String longIntro) {
        this.longIntro = longIntro;
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

    public String getMajorCate() {
        return majorCate;
    }

    public void setMajorCate(String majorCate) {
        this.majorCate = majorCate;
    }

    public String getMinorCate() {
        return minorCate;
    }

    public void setMinorCate(String minorCate) {
        this.minorCate = minorCate;
    }

    public int getCurrency() {
        return currency;
    }

    public void setCurrency(int currency) {
        this.currency = currency;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean is_le() {
        return _le;
    }

    public void set_le(boolean _le) {
        this._le = _le;
    }

    public boolean isAllowMonthly() {
        return allowMonthly;
    }

    public void setAllowMonthly(boolean allowMonthly) {
        this.allowMonthly = allowMonthly;
    }

    public boolean isAllowVoucher() {
        return allowVoucher;
    }

    public void setAllowVoucher(boolean allowVoucher) {
        this.allowVoucher = allowVoucher;
    }

    public boolean isAllowBeanVoucher() {
        return allowBeanVoucher;
    }

    public void setAllowBeanVoucher(boolean allowBeanVoucher) {
        this.allowBeanVoucher = allowBeanVoucher;
    }

    public boolean isHasCp() {
        return hasCp;
    }

    public void setHasCp(boolean hasCp) {
        this.hasCp = hasCp;
    }

    public int getPostCount() {
        return postCount;
    }

    public void setPostCount(int postCount) {
        this.postCount = postCount;
    }

    public int getLatelyFollower() {
        return latelyFollower;
    }

    public void setLatelyFollower(int latelyFollower) {
        this.latelyFollower = latelyFollower;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(int followerCount) {
        this.followerCount = followerCount;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getSerializeWordCount() {
        return serializeWordCount;
    }

    public void setSerializeWordCount(int serializeWordCount) {
        this.serializeWordCount = serializeWordCount;
    }

    public String getRetentionRatio() {
        return retentionRatio;
    }

    public void setRetentionRatio(String retentionRatio) {
        this.retentionRatio = retentionRatio;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public boolean isSerial() {
        return isSerial;
    }

    public void setSerial(boolean serial) {
        isSerial = serial;
    }

    public int getChaptersCount() {
        return chaptersCount;
    }

    public void setChaptersCount(int chaptersCount) {
        this.chaptersCount = chaptersCount;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public void setLastChapter(String lastChapter) {
        this.lastChapter = lastChapter;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean isDonate() {
        return donate;
    }

    public void setDonate(boolean donate) {
        this.donate = donate;
    }

    private boolean donate;
}
