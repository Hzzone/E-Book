package com.example.hzzone.e_book.Activity;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.hzzone.e_book.R;

import shem.com.materiallogin.DefaultLoginView;
import shem.com.materiallogin.DefaultRegisterView;
import shem.com.materiallogin.MaterialLoginView;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final MaterialLoginView login = (MaterialLoginView) findViewById(R.id.login);
        ((DefaultLoginView)login.getLoginView()).setListener(new DefaultLoginView.DefaultLoginViewListener() {
            @Override
            public void onLogin(TextInputLayout loginUser, TextInputLayout loginPass) {
                //Handle login
                String user_name = loginUser.getEditText().getText().toString();
                String user_passwd = loginPass.getEditText().getText().toString();
                if (user_name.isEmpty()) {
                    loginUser.setError("用户名不能为空");
                    return;
                }
                loginUser.setError("");

                //TODO 密码验证
                if (user_name!="1@qq.com"&&user_passwd!="1234") {
                    loginPass.setError("Wrong password");
                    return;
                }
                loginPass.setError("");

                Snackbar.make(login, "登录成功", Snackbar.LENGTH_LONG).show();
                Log.d(TAG, "onLogin: 登录:" + "用户名:"+user_name+ " 密码:"+user_passwd);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("user_name", user_name);
                intent.putExtra("user_passwd", user_passwd);
                startActivity(intent);
            }
        });

        ((DefaultRegisterView)login.getRegisterView()).setListener(new DefaultRegisterView.DefaultRegisterViewListener() {
            @Override
            public void onRegister(TextInputLayout registerUser, TextInputLayout registerPass, TextInputLayout registerPassRep) {
                //Handle register
                String user_name = registerUser.getEditText().getText().toString();
                String user_passwd = registerPass.getEditText().getText().toString();
                String user_passwdRep = registerPassRep.getEditText().getText().toString();
                Log.d(TAG, "onLogin: 登录:" + "用户名:"+user_name+ " 密码:"+ user_passwd+" 确认密码:"+user_passwdRep);
            }
        });
    }
}
