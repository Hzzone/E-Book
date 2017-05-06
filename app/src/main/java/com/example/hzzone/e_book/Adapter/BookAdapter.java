package com.example.hzzone.e_book.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.hzzone.e_book.Data.Book;

import java.util.Vector;

/**
 * Created by Hzzone on 2017/5/7.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    private int resourceID;

    public BookAdapter(Context context, int ViewResourceID,
                           Vector<Book> objects){
        super(context, ViewResourceID, objects);
        resourceID = ViewResourceID;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book = getItem(position);
        View view;
        //利用缓存提高效率
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceID, parent
                    , false);
        }else {
            view = convertView;
        }
//        ImageView bookinfo_img = (ImageView)view.findViewById(R.id.bookinfo_img);
//        TextView bookinfo_name = (TextView) view.findViewById(R.id.bookinfo_name);
//        bookinfo_img.setImageBitmap(bookinfo.getPic());
//        bookinfo_name.setText(bookinfo.getBookName());
        //TODO 设置列表显示
        return view;
    }
}
