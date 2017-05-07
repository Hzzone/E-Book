package com.example.hzzone.e_book.Spider;

import com.alibaba.fastjson.JSON;
import com.example.hzzone.e_book.JsonData.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Hzzone on 2017/5/6.
 * This class is to get the book many detail info
 */

public class Content {
    public Content(String json) {
        this.json = json;
    }

    private String json;
    //获得data中的数据
    private  String getData(){
        return JSON.parseObject(json, OuterMostData.class).getData();
    }

    //通过bookid获得书源列表
    public List<BookResource> getBookResource() {
        List<BookResource> bookResources = new ArrayList<BookResource>();
        try{
             bookResources = JSON.parseArray(getData(),
                    BookResource.class);
            return bookResources;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //通过书源获得书籍章节列表，形如http://novel.juhe.im/book-chapters/+bookid
    public List<Chapter> getBookChapterList() {
        List<Chapter> bookChapterList = new ArrayList<Chapter>();
        try{
            GetChapterList getChapterList = JSON.parseObject(getData(),
                    GetChapterList.class);
            bookChapterList = JSON.parseArray(getChapterList.getChapters(), Chapter.class);
            return bookChapterList;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //通过章节id获取章节内容，形式为http://novel.juhe.im/chapters/http%3A%2F%2Fvip.zhuishushenqi.com%2Fchapter%2F + id + %3Fcv%3D1486473051386

    public ChapterContent getChapterContent() {
        try{
            return JSON.parseObject(JSON.parseObject(getData(), OutsideChapterContent.class).getChapter()
                    , ChapterContent.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //通过书籍关键字获取搜索结果，形如http://novel.juhe.im/search?keyword=遮天
    //获得bookid等信息
    public List<SearchResult> getSearchResults() {
        List<SearchResult> bookResults = new ArrayList<SearchResult>();
        try{
            GetBookSearchResult getBookSearchResult = JSON.parseObject(getData(),
                    GetBookSearchResult.class);
            String s = getBookSearchResult.getBooks();
//            bookResults = JSON.parseArray(getBookSearchResult.getBooks(), SearchResult.class);
            Gson gson = new Gson();
            bookResults = gson.fromJson(s, new TypeToken<List<SearchResult>>() {}.getType());
            return bookResults;
        }catch (Exception e){
            e.printStackTrace();
        }
        //TODO 如果没有搜索到，对异常进行处理
        return null;
    }

    //通过bookid获得小说的介绍详情
    public BookDetails getBookDetails(){
        try{
            return JSON.parseObject(getData(), BookDetails.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        //TODO 如果没有搜索到，对异常进行处理
        return null;
    }

}
