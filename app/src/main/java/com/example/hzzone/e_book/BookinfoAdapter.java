package com.example.hzzone.e_book;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Hzzone on 2017/3/2.
 */

public class BookinfoAdapter extends ArrayAdapter<Bookinfo> {
    private int resourceID;

    public BookinfoAdapter(Context context, int textViewResourceID,
                           Vector<Bookinfo> objects){
        super(context, textViewResourceID, objects);
        resourceID = textViewResourceID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Bookinfo bookinfo = getItem(position);
        View view;
        //利用缓存提高效率
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceID, parent
                , false);
        }else {
            view = convertView;
        }
        ImageView bookinfo_img = (ImageView)view.findViewById(R.id.bookinfo_img);
        TextView bookinfo_name = (TextView) view.findViewById(R.id.bookinfo_name);
        bookinfo_img.setImageBitmap(bookinfo.getPic());
        bookinfo_name.setText(bookinfo.getBookName());
        return view;
    }
}
