package com.cuckoo.cuckooweather.activity;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import com.cuckoo.cuckooweather.R;
import com.cuckoo.cuckooweather.network.UserManagerHandle;
import com.loopj.android.http.JsonHttpResponseHandler;
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
    private EditText nickname_et = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username_et = (EditText)findViewById(R.id.username_et);
        password_et = (EditText)findViewById(R.id.password_et);
        email_et = (EditText)findViewById(R.id.mail_et);
        nickname_et = (EditText)findViewById(R.id.nickname_et);

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
                user.setNickname(nickname_et.getText().toString());
                
                UserManagerHandle userManagerHandle = new UserManagerHandle();
                userManagerHandle.register(user, new JsonHttpResponseHandler() {

                	@Override
					public void onFailure(int statusCode, Header[] headers,
							Throwable throwable, JSONObject errorResponse) {
						// TODO Auto-generated method stub
						super.onFailure(statusCode, headers, throwable, errorResponse);
						
						display("error:" + errorResponse.toString() + "error status:" + statusCode);
					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							JSONObject response) {
						// TODO Auto-generated method stub
						super.onSuccess(statusCode, headers, response);
						
						Intent intent = new Intent();
	                    intent.setClass(RegisterActivity.this, MainActivity.class);
	                    startActivity(intent);
	                    
						display("status:" + statusCode + " response: " + response.toString());
						System.out.println("status:" + statusCode + " response: " + response.toString());
					}
                });
            }
        });
    }


    public void display(String string) {
        Toast.makeText(RegisterActivity.this, string, Toast.LENGTH_SHORT).show();
    }
}
