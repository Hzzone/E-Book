package com.example.hzzone.e_book.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hzzone.e_book.Data.Book;
import com.example.hzzone.e_book.R;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Hzzone on 2017/5/7.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    private int resourceID;

    public BookAdapter(Context context, int ViewResourceID,
                           List<Book> objects){
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
        //TODO 设置列表显示
        Picasso.with(view.getContext()).load(book.getCover_URL()).into(
            (ImageView) view.findViewById(R.id.bookinfo_img)
        );
        ((TextView)view.findViewById(R.id.book_author)).setText(book.getAuthor());
        ((TextView)view.findViewById(R.id.book_latest_chapter)).setText(book.getLast_chapter());
        ((TextView)view.findViewById(R.id.book_name)).setText(book.getBook_name());

        return view;
    }
}
