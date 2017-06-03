package com.example.hzzone.e_book.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzzone.e_book.Data.Book;
import com.example.hzzone.e_book.JsonData.Chapter;
import com.example.hzzone.e_book.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hzzone on 2017/5/7.
 */

public class ChapterAdapter extends ArrayAdapter<Chapter> {
    private int resourceID;

    public ChapterAdapter(Context context, int ViewResourceID,
                       List<Chapter> objects){
        super(context, ViewResourceID, objects);
        resourceID = ViewResourceID;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Chapter chapter = getItem(position);
        View view;
        //利用缓存提高效率
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceID, parent
                    , false);
        }else {
            view = convertView;
        }
        //TODO 设置列表显示章节目录
        ((TextView)view.findViewById(R.id._chapter)).setText(chapter.getTitle());

        return view;
    }
}
