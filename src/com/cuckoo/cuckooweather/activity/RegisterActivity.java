package com.cuckoo.cuckooweather.activity;

import com.cuckoo.cuckooweather.R;
import com.suckoo.cuckoo.weather.model.User;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
    private Button registerButton;
    private EditText username_et = null;
    private EditText password_et = null;
    private EditText email_et = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username_et = (EditText)findViewById(R.id.username_et);
        password_et = (EditText)findViewById(R.id.password_et);
        email_et = (EditText)findViewById(R.id.mail_et);

        //注册按钮
        registerButton = (Button)findViewById(R.id.btn_register_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //注册
                User user = new User();
                user.setUsername(username_et.getText().toString());
                user.setPassword(password_et.getText().toString());
                user.setEmail(email_et.getText().toString());
                
                //判断是否为空值
//                if (user.isEmpty()) {
//                    display("用户名或者密码不能为空");
//                } else {
//                    UserDao userDao = new UserDao(RegisterActivity.this);
//                    userDao.insertUser(user);
//
//                    Intent intent = new Intent();
//                    intent.setClass(RegisterActivity.this, MainActivity.class);
//                    startActivity(intent);
//                }
            }
        });
    }


    public void display(String string) {
        Toast.makeText(RegisterActivity.this, string, Toast.LENGTH_SHORT).show();
    }
}
