package com.example.hzzone.e_book.Data;

import android.graphics.Bitmap;

import org.litepal.annotation.Column;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Hzzone on 2017/5/7.
 */

public class User extends DataSupport{
    @Column(unique = true, nullable = false)
    private String user_account;
    @Column(nullable = false)
    private String user_password;


    @Override
    public String toString() {
        return "User{" +
                "user_account='" + user_account + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_name='" + user_name + '\'' +
                '}';
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Column(nullable = false)
    private String user_name;
}
