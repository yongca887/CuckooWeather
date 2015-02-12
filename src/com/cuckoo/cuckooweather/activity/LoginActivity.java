package com.cuckoo.cuckooweather.activity;

import org.apache.http.Header;
import org.json.JSONObject;

import com.cuckoo.cuckooweather.R;
import com.cuckoo.cuckooweather.network.UserManagerHandle;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
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

public class LoginActivity extends Activity {
    private Button loginButton;
    private Button registerButton;

    private EditText username_et;
    private EditText password_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //用户名、密码
        username_et = (EditText)findViewById(R.id.edit_username);
        password_et = (EditText)findViewById(R.id.edit_password);

        //登录按钮
        loginButton = (Button)findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = username_et.getText().toString();
                String password = password_et.getText().toString();

                //判断是否为空值
                if (username.equals("") || (password.equals(""))) {
                    display("用户名或者密码不能为空");
                } else {
                    
                    //登录验证
                	UserManagerHandle userManagerHandle = new UserManagerHandle();
                	User user = new User();
                	user.setUsername(username);
                	user.setPassword(password);
                	
                	userManagerHandle.login(user, new JsonHttpResponseHandler() {

						@Override
						public void onFailure(int statusCode, Header[] headers,
								Throwable throwable, JSONObject errorResponse) {
							// TODO Auto-generated method stub
							super.onFailure(statusCode, headers, throwable, errorResponse);
							
							display("登录失败，请检查账户和密码是否填写错误！");
							
							display("error:" + errorResponse.toString() + "error status:" + statusCode);
						}

						@Override
						public void onSuccess(int statusCode, Header[] headers,
								JSONObject response) {
							// TODO Auto-generated method stub
							super.onSuccess(statusCode, headers, response);
							
							Intent intent = new Intent();
	                        intent.setClass(LoginActivity.this, MainActivity.class);
	                        startActivity(intent);
							
	                        display("登录成功");
//							display("status:" + statusCode + " response: " + response.toString());
//							System.out.println("status:" + statusCode + " response: " + response.toString());
						}
                	});
                }
            }
        });
        //注册按钮
        registerButton = (Button)findViewById(R.id.btn_register);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setClass(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void display(String string) {
        Toast.makeText(LoginActivity.this, string, Toast.LENGTH_SHORT).show();
    }
}
